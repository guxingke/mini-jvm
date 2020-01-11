package com.gxk.jvm.nativebridge.java.util.concurrent.atomic;

import com.gxk.jvm.rtda.heap.Heap;

public abstract class AtomicReferenceFieldUpdater {

  public static void registerNative0() {
    Heap.registerMethod("java/util/concurrent/atomic/AtomicReferenceFieldUpdater_newUpdater_(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", frame -> {
      frame.popRef();
      frame.popRef();
      frame.popRef();
      frame.pushRef(null);
    });
  }
}
