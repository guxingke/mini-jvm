package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.MetaSpace;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KObject;

public abstract class IntegerBridge {

  public static void registerNatives0() {
    MetaSpace.registerMethod("java/lang/Integer_valueOf_(I)Ljava/lang/Integer;", frame -> {
      KClass clazz = MetaSpace.findClass("java/lang/Integer");
      KObject kObject = clazz.newObject();
      kObject.setField("value", "I", new Slot[] {new Slot(frame.popInt(), Slot.INT)});
      frame.pushRef(kObject);
    });
  }
}
