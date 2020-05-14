package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KClass;
import com.gxk.jvm.rtda.memory.MethodArea;

public abstract class IntegerBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/lang/Integer_valueOf_(I)Ljava/lang/Integer;", frame -> {
      KClass clazz = MethodArea.findClass("java/lang/Integer");
      Long kObject = clazz.newObject();
      Heap.load(kObject).setField("value", "I", new Slot[]{new Slot(frame.popInt(), Slot.INT)});
      frame.pushRef(kObject);
    });
  }
}
