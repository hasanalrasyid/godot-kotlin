// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY! ALL CHANGES TO IT WILL BE OVERWRITTEN ON EACH BUILD
package godot

import godot.icalls._icall_Long
import godot.icalls._icall_StreamPeer
import godot.icalls._icall_Unit_Long
import godot.icalls._icall_Unit_Object
import godot.internal.utils.getMethodBind
import godot.internal.utils.invokeConstructor
import kotlin.Long
import kotlinx.cinterop.COpaquePointer

open class PacketPeerStream : PacketPeer() {
  open var inputBufferMaxSize: Long
    get() {
      val mb = getMethodBind("PacketPeerStream","get_input_buffer_max_size")
      return _icall_Long(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("PacketPeerStream","set_input_buffer_max_size")
      _icall_Unit_Long(mb, this.ptr, value)
    }

  open var outputBufferMaxSize: Long
    get() {
      val mb = getMethodBind("PacketPeerStream","get_output_buffer_max_size")
      return _icall_Long(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("PacketPeerStream","set_output_buffer_max_size")
      _icall_Unit_Long(mb, this.ptr, value)
    }

  open var streamPeer: StreamPeer
    get() {
      val mb = getMethodBind("PacketPeerStream","get_stream_peer")
      return _icall_StreamPeer(mb, this.ptr)
    }
    set(value) {
      val mb = getMethodBind("PacketPeerStream","set_stream_peer")
      _icall_Unit_Object(mb, this.ptr, value)
    }

  override fun __new(): COpaquePointer = invokeConstructor("PacketPeerStream", "PacketPeerStream")

  open fun getInputBufferMaxSize(): Long {
    val mb = getMethodBind("PacketPeerStream","get_input_buffer_max_size")
    return _icall_Long( mb, this.ptr)
  }

  open fun getOutputBufferMaxSize(): Long {
    val mb = getMethodBind("PacketPeerStream","get_output_buffer_max_size")
    return _icall_Long( mb, this.ptr)
  }

  open fun getStreamPeer(): StreamPeer {
    val mb = getMethodBind("PacketPeerStream","get_stream_peer")
    return _icall_StreamPeer( mb, this.ptr)
  }

  open fun setInputBufferMaxSize(maxSizeBytes: Long) {
    val mb = getMethodBind("PacketPeerStream","set_input_buffer_max_size")
    _icall_Unit_Long( mb, this.ptr, maxSizeBytes)
  }

  open fun setOutputBufferMaxSize(maxSizeBytes: Long) {
    val mb = getMethodBind("PacketPeerStream","set_output_buffer_max_size")
    _icall_Unit_Long( mb, this.ptr, maxSizeBytes)
  }

  open fun setStreamPeer(peer: StreamPeer) {
    val mb = getMethodBind("PacketPeerStream","set_stream_peer")
    _icall_Unit_Object( mb, this.ptr, peer)
  }
}
