// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.VisualShaderNodeCubeMap
import godot.icalls._icall_CubeMap
import godot.icalls._icall_Long
import godot.icalls._icall_Unit_Long
import godot.icalls._icall_Unit_Object
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Long
import kotlinx.cinterop.COpaquePointer

open class VisualShaderNodeCubeMap : VisualShaderNode() {
  open var cubeMap: CubeMap
    get() {
      val mb = getMethodBind("VisualShaderNodeCubeMap","get_cube_map")
      return _icall_CubeMap(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("VisualShaderNodeCubeMap","set_cube_map")
      _icall_Unit_Object(mb, this.ptr, value)
    }

  open var source: Long
    get() {
      val mb = getMethodBind("VisualShaderNodeCubeMap","get_source")
      return _icall_Long(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("VisualShaderNodeCubeMap","set_source")
      _icall_Unit_Long(mb, this.ptr, value)
    }

  open var textureType: Long
    get() {
      val mb = getMethodBind("VisualShaderNodeCubeMap","get_texture_type")
      return _icall_Long(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("VisualShaderNodeCubeMap","set_texture_type")
      _icall_Unit_Long(mb, this.ptr, value)
    }

  override fun __new(): COpaquePointer = invokeConstructor("VisualShaderNodeCubeMap",
      "VisualShaderNodeCubeMap")

  open fun getCubeMap(): CubeMap {
    val mb = getMethodBind("VisualShaderNodeCubeMap","get_cube_map")
    return _icall_CubeMap( mb, this.ptr)
  }

  open fun getSource(): VisualShaderNodeCubeMap.Source {
    val mb = getMethodBind("VisualShaderNodeCubeMap","get_source")
    return VisualShaderNodeCubeMap.Source.from( _icall_Long( mb, this.ptr))
  }

  open fun getTextureType(): VisualShaderNodeCubeMap.TextureType {
    val mb = getMethodBind("VisualShaderNodeCubeMap","get_texture_type")
    return VisualShaderNodeCubeMap.TextureType.from( _icall_Long( mb, this.ptr))
  }

  open fun setCubeMap(value: CubeMap) {
    val mb = getMethodBind("VisualShaderNodeCubeMap","set_cube_map")
    _icall_Unit_Object( mb, this.ptr, value)
  }

  open fun setSource(value: Long) {
    val mb = getMethodBind("VisualShaderNodeCubeMap","set_source")
    _icall_Unit_Long( mb, this.ptr, value)
  }

  open fun setTextureType(value: Long) {
    val mb = getMethodBind("VisualShaderNodeCubeMap","set_texture_type")
    _icall_Unit_Long( mb, this.ptr, value)
  }

  enum class TextureType(
    id: Long
  ) {
    TYPE_DATA(0),

    TYPE_COLOR(1),

    TYPE_NORMALMAP(2);

    val id: Long
    init {
      this.id = id
    }

    companion object {
      fun from(value: Long) = values().single { it.id == value }
    }
  }

  enum class Source(
    id: Long
  ) {
    SOURCE_TEXTURE(0),

    SOURCE_PORT(1);

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
