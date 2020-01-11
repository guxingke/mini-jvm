package com.gxk.jvm.nativebridge.sun.misc;

import com.gxk.jvm.rtda.heap.Heap;

public abstract class VersionBridge {

  public static void registerNative0() {
    // static
    Heap.registerMethod("sun/misc/Version_init_()V", frame -> {
      
    });
  }
}
