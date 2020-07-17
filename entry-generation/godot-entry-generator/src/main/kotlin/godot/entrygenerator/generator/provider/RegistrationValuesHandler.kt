package godot.entrygenerator.generator.provider

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName
import godot.entrygenerator.extension.assignmentPsi
import godot.entrygenerator.extension.getAnnotationValue
import godot.entrygenerator.extension.getPropertyHintAnnotation
import godot.entrygenerator.model.REGISTER_PROPERTY_ANNOTATION
import godot.entrygenerator.model.REGISTER_PROPERTY_ANNOTATION_VISIBLE_IN_EDITOR_ARGUMENT
import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.js.resolve.diagnostics.findPsi
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject
import org.jetbrains.kotlin.psi.psiUtil.referenceExpression
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.bindingContextUtil.getReferenceTargets
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.resolve.descriptorUtil.isCompanionObject
import org.jetbrains.kotlin.resolve.descriptorUtil.parents
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedClassConstructorDescriptor
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor

abstract class RegistrationValuesHandler(
    val propertyDescriptor: PropertyDescriptor,
    val bindingContext: BindingContext
) {
    internal val propertyHintAnnotation = propertyDescriptor.getPropertyHintAnnotation()

    abstract fun getPropertyTypeHint(): ClassName
    abstract fun getHintString(): String

    init {
        checkHintAnnotationUsage()
    }

    private fun checkHintAnnotationUsage() {
        if (!propertyDescriptor.annotations.getAnnotationValue(
                REGISTER_PROPERTY_ANNOTATION,
                REGISTER_PROPERTY_ANNOTATION_VISIBLE_IN_EDITOR_ARGUMENT,
                true
            ) && propertyHintAnnotation != null
        ) {
            throw IllegalStateException("You added the type hint annotation ${propertyHintAnnotation.fqName} to the property ${propertyDescriptor.name}. But the @RegisterProperty annotation is either not present or the isVisibleInEditor flag is not set to true")
        }
        if (!propertyDescriptor.isVar) {
            throw IllegalStateException("You try to register the immutable property ${propertyDescriptor.fqNameSafe} with @RegisterProperty. This is not supported! Each property that you register has to be mutable. Use var or lateinit var.")
        }
    }

    open fun getDefaultValue(): Pair<String, Array<out Any>> {
        if (propertyDescriptor.isLateInit || !isVisibleInEditor()) {
            return "%L" to arrayOf("null")
        }
        val defaultValue = getDefaultValueExpression(propertyDescriptor.assignmentPsi)
        if (defaultValue == null) {
            throw IllegalStateException("") //TODO: error
        }
        val params = mutableListOf<Any>()
        params.add(ClassName("godot.core", "Variant"))
        params.addAll(defaultValue.second)
        return "%T(${defaultValue.first})" to params.toTypedArray()
    }

    internal fun isVisibleInEditor(): Boolean {
        return propertyDescriptor.annotations.getAnnotationValue(
            REGISTER_PROPERTY_ANNOTATION,
            REGISTER_PROPERTY_ANNOTATION_VISIBLE_IN_EDITOR_ARGUMENT,
            true
        )
    }

    private fun getDefaultValueExpression(expression: KtExpression): Pair<String, Array<out Any>>? {
        if (expression is KtConstantExpression) {
            return "%L" to arrayOf(expression.text)
        } else if (expression is KtStringTemplateExpression && !expression.hasInterpolation()) {
            return "%S" to arrayOf(expression.text.removeSurrounding("\""))
        } else if (expression is KtDotQualifiedExpression) {
            val receiver = expression.receiverExpression
            val receiverRef = receiver.getReferenceTargets(bindingContext).firstOrNull()

            if (receiverRef != null) {
                val psi = receiverRef.findPsi()
                // TODO: receiver ref might be a deserialized descriptor, fix this once we have core classes
                if (psi is KtClass && psi.isEnum()) {
                    val fqName = psi.fqName
                    require(fqName != null)
                    val pkg = fqName.parent().asString()
                    val className = fqName.shortName().asString()
                    return "%T.%L" to arrayOf(ClassName(pkg, className), expression.selectorExpression!!.text)
                }
            } else if (receiver is KtStringTemplateExpression) {
                val selectorExpression = expression
                    .selectorExpression
                    ?.referenceExpression()
                    ?.getReferenceTargets(bindingContext)
                    ?.firstOrNull()

                if (selectorExpression?.fqNameSafe?.asString() == "kotlin.text.trimIndent") {
                    val packagePath = selectorExpression
                        .fqNameSafe
                        .asString()
                        .replace(".${selectorExpression.name}", "")

                    return "%L.%M()" to arrayOf(
                        receiver.text,
                        MemberName(packagePath, selectorExpression.name.asString())
                    )
                }
            }
        } else if (expression is KtCallExpression) {
            val ref = expression
                .referenceExpression()
                ?.getReferenceTargets(bindingContext)
                ?.firstOrNull()

            if (ref != null) {
                val psi = ref.findPsi()
                val transformedArgs = expression
                    .valueArguments
                    .mapNotNull { it.getArgumentExpression() }
                    .map { getDefaultValueExpression(it) }

                // if an arg is null, then it means that it contained a non static reference
                var hasNullArg = false
                for (arg in transformedArgs) {
                    if (arg == null) {
                        hasNullArg = true
                        break
                    }
                }

                if (psi is KtConstructor<*> && !hasNullArg) {
                    val fqName = psi.containingClassOrObject!!.fqName
                    require(fqName != null)
                    val pkg = fqName.parent().asString()
                    val className = fqName.shortName().asString()
                    val params = mutableListOf<Any>()
                    params.add(ClassName(pkg, className))
                    transformedArgs.forEach { params.addAll(it!!.second) }
                    return "%T(${transformedArgs.joinToString { it!!.first }})" to params.toTypedArray()
                } else if (ref is DeserializedClassConstructorDescriptor && !hasNullArg) {
                    val fqName = ref.constructedClass.fqNameSafe
                    val pkg = fqName.parent().asString()
                    val className = fqName.shortName().asString()
                    val params = mutableListOf<Any>()
                    params.add(ClassName(pkg, className))
                    transformedArgs.forEach { params.addAll(it!!.second) }
                    return "%T(${transformedArgs.joinToString { it!!.first }})" to params.toTypedArray()
                } else if (ref is DeserializedSimpleFunctionDescriptor && ref.fqNameSafe.asString() == "godot.core.variantArrayOf") {
                    val fqName = ref.fqNameSafe
                    val pkg = fqName.parent().asString()
                    val functionName = fqName.shortName().asString()
                    val params = mutableListOf<Any>()
                    params.add(MemberName(pkg, functionName))
                    transformedArgs.forEach { params.addAll(it!!.second) }
                    return "%M(${transformedArgs.joinToString { it!!.first }})" to params.toTypedArray()
                } else if (expression.getType(bindingContext)?.let(KotlinBuiltIns::isSetOrNullableSet) == true) {
                    //setOf -> ref is null in this case
                    val params = mutableListOf<Any>()
                    params.add(expression.children.first().text)
                    transformedArgs.forEach { params.addAll(it!!.second) }
                    return "%L(${transformedArgs.joinToString { it!!.first }})" to params.toTypedArray()
                }
            }
        } else if (expression is KtBinaryExpression) {
            val assignment = expression
                .children
                .map { getDefaultValueExpression(it as KtExpression) }

            if (!assignment.any { it == null }) {
                return assignment.joinToString("·") { it!!.first } to
                    assignment.map { it!!.second }.toTypedArray().flatten().toTypedArray()
            }
        } else if (expression is KtNameReferenceExpression) {
            val ref = expression
                .referenceExpression()
                ?.getReferenceTargets(bindingContext)
                ?.firstOrNull()

            if (ref !is PropertyDescriptor) {
                throw IllegalStateException("You tried to register property ${propertyDescriptor.fqNameSafe} with a reference (${expression.text}) which is not a property. Default values which are references have to be properties. Functions are not yet supported!")
            }
            if (!ref.visibility.isPublicAPI && ref.visibility.name != "internal") {
                throw IllegalStateException("You tried to register property ${propertyDescriptor.fqNameSafe} with a reference (${expression.text}) which is not public. Default values which are references have to be public or at least internal")
            }
            if (!ref.isConst && !ref.parents.first().isCompanionObject()) {
                throw IllegalStateException("You tried to register property ${propertyDescriptor.fqNameSafe} with a reference (${expression.text}) which is not a const or static. Default values which are references have to be compile time constants or have to be static")
            }

            return "%M" to arrayOf(MemberName(ref.fqNameSafe.parent().asString(), ref.name.asString()))
        } else if (expression is KtOperationReferenceExpression) {
            return "%L" to arrayOf(expression.text)
        }

        return null
    }
}