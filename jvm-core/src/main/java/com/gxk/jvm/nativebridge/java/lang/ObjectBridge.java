package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KObject;

public abstract class ObjectBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/lang/Object_registerNatives_()V", (frame) -> {
    });
    MethodArea.registerMethod("java/lang/Object_clone_()Ljava/lang/Object;", (frame) -> {
      KObject obj = Heap.load(frame.popRef());
      Long newObj = null;
      try {
        newObj = obj.clone();
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
      }
      frame.pushRef(newObj);
    });
    MethodArea.registerMethod("java/lang/Object_getClass_()Ljava/lang/Class;", (frame) -> {
      KObject val = Heap.load(frame.popRef());
      frame.pushRef(val.clazz.getRuntimeClass());
    });

    MethodArea.registerMethod("java/lang/Object_wait_(J)V", (frame) -> {
    });
    MethodArea.registerMethod("java/lang/Object_notify_()V", (frame) -> {
    });
    MethodArea.registerMethod("java/lang/Object_notifyAll_()V", (frame) -> {
    });
    MethodArea.registerMethod("java/lang/Object_hashCode_()I", (frame) -> {
      int val = frame.popRef().hashCode();
      frame.pushInt(val);
    });
  }
}
