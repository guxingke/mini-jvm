package com.gxk.jvm.nativebridge.java.sum.misc;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.MethodArea;

public abstract class ReflectionBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("sun/reflect/Reflection_getCallerClass_()Ljava/lang/Class;", frame -> {
      Frame callerFrame = frame.thread.callerFrame();
      Long cls = callerFrame.method.clazz.getRuntimeClass();
      frame.pushRef(cls);
    });
  }
}
