package com.gxk.jvm.nativebridge.sun.misc;

import com.gxk.jvm.rtda.heap.Heap;

public abstract class VmBridge {

  public static void registerNative0() {
    // static
    Heap.registerMethod("sun/misc/VM_saveAndRemoveProperties_(Ljava/util/Properties;)V", frame -> {
      frame.popRef();
    });
  }
}
