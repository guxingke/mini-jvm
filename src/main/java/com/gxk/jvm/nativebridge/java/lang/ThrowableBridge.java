package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.heap.Heap;

public abstract class ThrowableBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/Throwable_<clinit>_()V", frame -> {
    });
    Heap.registerMethod("java/lang/Throwable_<init>_(Ljava/lang/String)Ljava/lang/Throwable;", frame -> {
    });
    Heap.registerMethod("java/lang/Throwable_fillInStackTrace_(I)Ljava/lang/Throwable;", frame -> {
    });
    Heap.registerMethod("java/lang/Throwable_fillInStackTrace_()Ljava/lang/Throwable;", frame -> {
    });
    Heap.registerMethod("java/lang/Throwable_getStackTraceDepth_()I", frame -> {
    });
    Heap.registerMethod("java/lang/Throwable_getStackTraceElement_(I)Ljava/lang/StackTraceElement;", frame -> {
    });
  }
}
