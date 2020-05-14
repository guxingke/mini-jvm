package com.gxk.jvm.nativebridge.java.security;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KMethod;
import com.gxk.jvm.rtda.memory.KObject;

public abstract class AccessControllerBridge {

  public static void registerNative0() {
    // static
    MethodArea.registerMethod("java/security/AccessController_doPrivileged_(Ljava/security/PrivilegedAction;)Ljava/lang/Object;", frame -> {
      Long thisObj = frame.popRef();
      KMethod method = Heap.load(thisObj).clazz.getMethod("run", "()Ljava/lang/Object;");
      Frame newFrame = new Frame(method, frame.thread);
      newFrame.setRef(0, thisObj);
      frame.thread.pushFrame(newFrame);
    });
    // static
    MethodArea.registerMethod("java/security/AccessController_doPrivileged_(Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;", frame -> {
      Long thisObj = frame.popRef();
      KMethod method = Heap.load(thisObj).clazz.getMethod("run", "()Ljava/lang/Object;");
      Frame newFrame = new Frame(method, frame.thread);
      newFrame.setRef(0, thisObj);
      frame.thread.pushFrame(newFrame);
    });
  }
}
