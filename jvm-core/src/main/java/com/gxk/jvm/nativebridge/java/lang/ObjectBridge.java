package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.MetaSpace;
import com.gxk.jvm.rtda.heap.KObject;

public abstract class ObjectBridge {

  public static void registerNatives0() {
    MetaSpace.registerMethod("java/lang/Object_registerNatives_()V", (frame) -> {
    });
    MetaSpace.registerMethod("java/lang/Object_clone_()Ljava/lang/Object;", (frame) -> {
      KObject obj = (KObject) frame.popRef();
      java.lang.Object newObj = null;
      try {
        newObj = obj.clone();
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
      }
      frame.pushRef((KObject) newObj);
    });
    MetaSpace.registerMethod("java/lang/Object_getClass_()Ljava/lang/Class;", (frame) -> {
      KObject val = (KObject) frame.popRef();
      frame.pushRef(val.clazz.getRuntimeClass());
    });

    MetaSpace.registerMethod("java/lang/Object_wait_(J)V", (frame) -> {
    });
    MetaSpace.registerMethod("java/lang/Object_notify_()V", (frame) -> {
    });
    MetaSpace.registerMethod("java/lang/Object_notifyAll_()V", (frame) -> {
    });
    MetaSpace.registerMethod("java/lang/Object_hashCode_()I", (frame) -> {
      int val = frame.popRef().hashCode();
      frame.pushInt(val);
    });
  }
}
