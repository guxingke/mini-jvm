package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.UnionSlot;
import com.gxk.jvm.rtda.heap.Class;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.Instance;

public abstract class IntegerBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/Integer_valueOf_(I)Ljava/lang/Integer;", frame -> {
      Class clazz = Heap.findClass("java/lang/Integer");
      Instance instance = clazz.newInstance();
      final Integer val = frame.popInt();
      instance.setField("value", "I", UnionSlot.of(val));
      frame.pushRef(instance);
    });
  }
}
