package com.gxk.jvm.nativebridge.java.nio.charset;

import com.gxk.jvm.rtda.heap.Heap;

public abstract class CharsetBridge {

  public static void registerNative0() {
    // static
    Heap.registerMethod("java/nio/charset/Charset_atBugLevel_(Ljava/lang/String;)Z", frame -> {
      frame.popRef();
      // false
      frame.pushInt(0);
    });
  }
}
