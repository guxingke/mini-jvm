package com.gxk.jvm.nativebridge.sun.misc;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;

public abstract class ReflectionBridge {

  public static void registerNatives0() {
    Heap.registerMethod("sun/reflect/Reflection_getCallerClass_()Ljava/lang/Class;", frame -> {
      Frame callerFrame = frame.thread.callerFrame();
      Object cls = callerFrame.method.clazz.getRuntimeClass();
      frame.pushRef(cls);
    });
  }
}
