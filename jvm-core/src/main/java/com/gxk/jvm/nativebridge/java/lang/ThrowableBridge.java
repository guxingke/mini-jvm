package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.MetaSpace;

public abstract class ThrowableBridge {

  public static void registerNatives0() {
    MetaSpace.registerMethod("java/lang/Throwable_<clinit>_()V", frame -> {
    });
    MetaSpace.registerMethod("java/lang/Throwable_<init>_(Ljava/lang/String)Ljava/lang/Throwable;", frame -> {
    });
    MetaSpace.registerMethod("java/lang/Throwable_fillInStackTrace_(I)Ljava/lang/Throwable;", frame -> {
    });
    MetaSpace.registerMethod("java/lang/Throwable_fillInStackTrace_()Ljava/lang/Throwable;", frame -> {
    });
    MetaSpace.registerMethod("java/lang/Throwable_getStackTraceDepth_()I", frame -> {
    });
    MetaSpace.registerMethod("java/lang/Throwable_getStackTraceElement_(I)Ljava/lang/StackTraceElement;", frame -> {
    });
  }
}
