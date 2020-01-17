package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.heap.Heap;

public abstract class StringBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/String_intern_()Ljava/lang/String;", frame -> {
      System.out.println();
    });
  }
}
