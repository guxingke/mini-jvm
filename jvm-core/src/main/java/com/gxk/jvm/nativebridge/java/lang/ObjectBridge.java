package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.Instance;

public abstract class ObjectBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/Object_registerNatives_()V", (frame) -> {
    });
    Heap.registerMethod("java/lang/Object_clone_()Ljava/lang/Object;", (frame) -> {
      Instance obj = frame.popRef();
      java.lang.Object newObj = null;
      try {
        newObj = obj.clone();
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
      }
      frame.pushRef((Instance) newObj);
    });
    Heap.registerMethod("java/lang/Object_getClass_()Ljava/lang/Class;", (frame) -> {
      Instance val = frame.popRef();
      frame.pushRef(val.clazz.getRuntimeClass());
    });

    Heap.registerMethod("java/lang/Object_wait_(J)V", (frame) -> {
    });
    Heap.registerMethod("java/lang/Object_notify_()V", (frame) -> {
    });
    Heap.registerMethod("java/lang/Object_notifyAll_()V", (frame) -> {
    });
    Heap.registerMethod("java/lang/Object_hashCode_()I", (frame) -> {
      int val = frame.popRef().hashCode();
      frame.pushInt(val);
    });
  }
}
