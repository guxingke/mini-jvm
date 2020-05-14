package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.memory.MethodArea;

public abstract class ThrowableBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/lang/Throwable_<clinit>_()V", frame -> {
    });
    MethodArea
        .registerMethod("java/lang/Throwable_<init>_(Ljava/lang/String)Ljava/lang/Throwable;", frame -> {
    });
    MethodArea.registerMethod("java/lang/Throwable_fillInStackTrace_(I)Ljava/lang/Throwable;", frame -> {
    });
    MethodArea.registerMethod("java/lang/Throwable_fillInStackTrace_()Ljava/lang/Throwable;", frame -> {
    });
    MethodArea.registerMethod("java/lang/Throwable_getStackTraceDepth_()I", frame -> {
    });
    MethodArea.registerMethod("java/lang/Throwable_getStackTraceElement_(I)Ljava/lang/StackTraceElement;", frame -> {
    });
  }
}
