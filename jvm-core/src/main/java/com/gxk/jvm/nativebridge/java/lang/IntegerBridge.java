package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.Class;
import com.gxk.jvm.rtda.heap.KObject;

public abstract class IntegerBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/Integer_valueOf_(I)Ljava/lang/Integer;", frame -> {
      Class clazz = Heap.findClass("java/lang/Integer");
      KObject kObject = clazz.newObject();
      kObject.setField("value", "I", new Slot[] {new Slot(frame.popInt())});
      frame.pushRef(kObject);
    });
  }
}
