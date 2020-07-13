package com.gxk.jvm.nativebridge.java.security;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.MetaSpace;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;

public abstract class AccessControllerBridge {

  public static void registerNative0() {
    // static
    MetaSpace.registerMethod("java/security/AccessController_doPrivileged_(Ljava/security/PrivilegedAction;)Ljava/lang/Object;", frame -> {
      KObject thisObj = (KObject) frame.popRef();
      KMethod method = thisObj.clazz.getMethod("run", "()Ljava/lang/Object;");
      Frame newFrame = new Frame(method, frame.thread);
      newFrame.setRef(0, thisObj);
      frame.thread.pushFrame(newFrame);
    });
    // static
    MetaSpace.registerMethod("java/security/AccessController_doPrivileged_(Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;", frame -> {
      KObject thisObj = (KObject) frame.popRef();
      KMethod method = thisObj.clazz.getMethod("run", "()Ljava/lang/Object;");
      Frame newFrame = new Frame(method, frame.thread);
      newFrame.setRef(0, thisObj);
      frame.thread.pushFrame(newFrame);
    });
  }
}
