// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.icalls._icall_Double
import godot.icalls._icall_Unit_Double
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Double
import kotlinx.cinterop.COpaquePointer

open class AudioEffectStereoEnhance : AudioEffect() {
  open var panPullout: Double
    get() {
      val mb = getMethodBind("AudioEffectStereoEnhance","get_pan_pullout")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioEffectStereoEnhance","set_pan_pullout")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var surround: Double
    get() {
      val mb = getMethodBind("AudioEffectStereoEnhance","get_surround")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioEffectStereoEnhance","set_surround")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  open var timePulloutMs: Double
    get() {
      val mb = getMethodBind("AudioEffectStereoEnhance","get_time_pullout")
      return _icall_Double(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("AudioEffectStereoEnhance","set_time_pullout")
      _icall_Unit_Double(mb, this.ptr, value)
    }

  override fun __new(): COpaquePointer = invokeConstructor("AudioEffectStereoEnhance",
      "AudioEffectStereoEnhance")

  open fun getPanPullout(): Double {
    val mb = getMethodBind("AudioEffectStereoEnhance","get_pan_pullout")
    return _icall_Double( mb, this.ptr)
  }

  open fun getSurround(): Double {
    val mb = getMethodBind("AudioEffectStereoEnhance","get_surround")
    return _icall_Double( mb, this.ptr)
  }

  open fun getTimePullout(): Double {
    val mb = getMethodBind("AudioEffectStereoEnhance","get_time_pullout")
    return _icall_Double( mb, this.ptr)
  }

  open fun setPanPullout(amount: Double) {
    val mb = getMethodBind("AudioEffectStereoEnhance","set_pan_pullout")
    _icall_Unit_Double( mb, this.ptr, amount)
  }

  open fun setSurround(amount: Double) {
    val mb = getMethodBind("AudioEffectStereoEnhance","set_surround")
    _icall_Unit_Double( mb, this.ptr, amount)
  }

  open fun setTimePullout(amount: Double) {
    val mb = getMethodBind("AudioEffectStereoEnhance","set_time_pullout")
    _icall_Unit_Double( mb, this.ptr, amount)
  }
}
