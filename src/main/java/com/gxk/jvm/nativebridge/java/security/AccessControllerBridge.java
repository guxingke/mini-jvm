package com.gxk.jvm.nativebridge.java.security;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;

public abstract class AccessControllerBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/security/AccessController_doPrivileged_(Ljava/security/PrivilegedAction;)Ljava/lang/Object;", frame -> {
      KObject obj = (KObject) frame.popRef();
      KMethod run = obj.clazz.getMethod("run", "()Ljava/lang/String;");
      frame.thread.pushFrame(new Frame(run, frame.thread));
    });
  }
}
