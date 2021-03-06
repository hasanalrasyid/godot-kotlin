// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.PathFollow
import godot.icalls._icall_Boolean
import godot.icalls._icall_Double
import godot.icalls._icall_Long
import godot.icalls._icall_Unit_Boolean
import godot.icalls._icall_Unit_Double
import godot.icalls._icall_Unit_Long
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Boolean
import kotlin.Double
import kotlin.Long
import kotlinx.cinterop.COpaquePointer

open class PathFollow : Spatial() {
  open var cubicInterp: Boolean
    get() {
      val mb = getMethodBind("PathFollow","get_cubic_interpolation")
      return _icall_Boolean(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("PathFollow","set_cubic_interpolation")
      _icall_Unit_Boolean(mb, this.ptr, value)
    }

  open var hOffset: Double
    get() {
      val mb = getMethodBind("PathFollow","get_h_offset")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("PathFollow","set_h_offset")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var loop: Boolean
    get() {
      val mb = getMethodBind("PathFollow","has_loop")
      return _icall_Boolean(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("PathFollow","set_loop")
      _icall_Unit_Boolean(mb, this.ptr, value)
    }

  open var offset: Double
    get() {
      val mb = getMethodBind("PathFollow","get_offset")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("PathFollow","set_offset")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var rotationMode: Long
    get() {
      val mb = getMethodBind("PathFollow","get_rotation_mode")
      return _icall_Long(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("PathFollow","set_rotation_mode")
      _icall_Unit_Long(mb, this.ptr, value)
    }

  open var unitOffset: Double
    get() {
      val mb = getMethodBind("PathFollow","get_unit_offset")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("PathFollow","set_unit_offset")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var vOffset: Double
    get() {
      val mb = getMethodBind("PathFollow","get_v_offset")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("PathFollow","set_v_offset")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  override fun __new(): COpaquePointer = invokeConstructor("PathFollow", "PathFollow")

  open fun getCubicInterpolation(): Boolean {
    val mb = getMethodBind("PathFollow","get_cubic_interpolation")
    return _icall_Boolean( mb, this.ptr)
  }

  open fun getHOffset(): Double {
    val mb = getMethodBind("PathFollow","get_h_offset")
    return _icall_Double( mb, this.ptr)
  }

  open fun getOffset(): Double {
    val mb = getMethodBind("PathFollow","get_offset")
    return _icall_Double( mb, this.ptr)
  }

  open fun getRotationMode(): PathFollow.RotationMode {
    val mb = getMethodBind("PathFollow","get_rotation_mode")
    return PathFollow.RotationMode.from( _icall_Long( mb, this.ptr))
  }

  open fun getUnitOffset(): Double {
    val mb = getMethodBind("PathFollow","get_unit_offset")
    return _icall_Double( mb, this.ptr)
  }

  open fun getVOffset(): Double {
    val mb = getMethodBind("PathFollow","get_v_offset")
    return _icall_Double( mb, this.ptr)
  }

  open fun hasLoop(): Boolean {
    val mb = getMethodBind("PathFollow","has_loop")
    return _icall_Boolean( mb, this.ptr)
  }

  open fun setCubicInterpolation(enable: Boolean) {
    val mb = getMethodBind("PathFollow","set_cubic_interpolation")
    _icall_Unit_Boolean( mb, this.ptr, enable)
  }

  open fun setHOffset(hOffset: Double) {
    val mb = getMethodBind("PathFollow","set_h_offset")
    _icall_Unit_Double( mb, this.ptr, hOffset)
  }

  open fun setLoop(loop: Boolean) {
    val mb = getMethodBind("PathFollow","set_loop")
    _icall_Unit_Boolean( mb, this.ptr, loop)
  }

  open fun setOffset(offset: Double) {
    val mb = getMethodBind("PathFollow","set_offset")
    _icall_Unit_Double( mb, this.ptr, offset)
  }

  open fun setRotationMode(rotationMode: Long) {
    val mb = getMethodBind("PathFollow","set_rotation_mode")
    _icall_Unit_Long( mb, this.ptr, rotationMode)
  }

  open fun setUnitOffset(unitOffset: Double) {
    val mb = getMethodBind("PathFollow","set_unit_offset")
    _icall_Unit_Double( mb, this.ptr, unitOffset)
  }

  open fun setVOffset(vOffset: Double) {
    val mb = getMethodBind("PathFollow","set_v_offset")
    _icall_Unit_Double( mb, this.ptr, vOffset)
  }

  enum class RotationMode(
    id: Long
  ) {
    ROTATION_NONE(0),

    ROTATION_Y(1),

    ROTATION_XY(2),

    ROTATION_XYZ(3),

    ROTATION_ORIENTED(4);

    val id: Long
    init {
      this.id = id
    }

    companion object {
      fun from(value: Long) = values().single { it.id == value }
    }
  }

  companion object
}
