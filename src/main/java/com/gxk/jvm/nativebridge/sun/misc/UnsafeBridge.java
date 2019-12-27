package com.gxk.jvm.nativebridge.sun.misc;

import com.gxk.jvm.rtda.heap.Heap;

public abstract class UnsafeBridge {

  public static void registerNatives0() {
    Heap.registerMethod("sun/misc/Unsafe_registerNatives_()V", frame -> {
    });
    Heap.registerMethod("sun/misc/Unsafe_getUnsafe_()Lsun/misc/Unsafe;", frame -> {
      frame.pushRef(null);
    });
  }
}
