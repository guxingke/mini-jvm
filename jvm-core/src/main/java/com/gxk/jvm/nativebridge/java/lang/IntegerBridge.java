package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.MetaSpace;
import com.gxk.jvm.rtda.UnionSlot;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KObject;

public abstract class IntegerBridge {

  public static void registerNatives0() {
    MetaSpace.registerMethod("java/lang/Integer_valueOf_(I)Ljava/lang/Integer;", frame -> {
      KClass clazz = MetaSpace.findClass("java/lang/Integer");
      KObject kObject = clazz.newObject();
      kObject.setField("value", "I", UnionSlot.of(frame.popInt()));
      frame.pushRef(kObject);
    });
  }
}
