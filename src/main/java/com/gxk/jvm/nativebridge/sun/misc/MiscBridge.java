package com.gxk.jvm.nativebridge.sun.misc;

import com.gxk.jvm.rtda.heap.Heap;

public abstract class MiscBridge {
  public static void registerNative0() {
    Heap.registerMethod("sun/nio/cs/StandardCharsets$Cache_<init>_()V", frame -> {
      frame.popRef();
    });
  }
}
