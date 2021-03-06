// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.icalls._icall_Boolean
import godot.icalls._icall_Long
import godot.icalls._icall_Unit_Boolean
import godot.icalls._icall_Unit_Long
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Boolean
import kotlin.Long
import kotlinx.cinterop.COpaquePointer

open class ViewportContainer : Container() {
  open var stretch: Boolean
    get() {
      val mb = getMethodBind("ViewportContainer","is_stretch_enabled")
      return _icall_Boolean(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("ViewportContainer","set_stretch")
      _icall_Unit_Boolean(mb, this.ptr, value)
    }

  open var stretchShrink: Long
    get() {
      val mb = getMethodBind("ViewportContainer","get_stretch_shrink")
      return _icall_Long(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("ViewportContainer","set_stretch_shrink")
      _icall_Unit_Long(mb, this.ptr, value)
    }

  override fun __new(): COpaquePointer = invokeConstructor("ViewportContainer", "ViewportContainer")

  override fun _input(event: InputEvent) {
  }

  override fun _unhandledInput(event: InputEvent) {
  }

  open fun getStretchShrink(): Long {
    val mb = getMethodBind("ViewportContainer","get_stretch_shrink")
    return _icall_Long( mb, this.ptr)
  }

  open fun isStretchEnabled(): Boolean {
    val mb = getMethodBind("ViewportContainer","is_stretch_enabled")
    return _icall_Boolean( mb, this.ptr)
  }

  open fun setStretch(enable: Boolean) {
    val mb = getMethodBind("ViewportContainer","set_stretch")
    _icall_Unit_Boolean( mb, this.ptr, enable)
  }

  open fun setStretchShrink(amount: Long) {
    val mb = getMethodBind("ViewportContainer","set_stretch_shrink")
    _icall_Unit_Long( mb, this.ptr, amount)
  }
}
