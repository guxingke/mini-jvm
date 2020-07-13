package com.gxk.jvm.nativebridge.java.util.concurrent;

import com.gxk.jvm.rtda.MetaSpace;

public abstract class AtomicLongBridge {

  public static void registerNatives0() {
    MetaSpace.registerMethod("java/util/concurrent/atomic/AtomicLong_VMSupportsCS8_()Z", frame -> {
      frame.pushInt(0);
    });
    MetaSpace.registerMethod("java/util/concurrent/atomic/AtomicLong_<clinit>_()V", frame -> {
    });
  }
}
