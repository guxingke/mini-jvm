package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KClass;

public abstract class DoubleBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/lang/Double_doubleToRawLongBits_(D)J", frame -> {
      java.lang.Double tmp = frame.popDouble();
      long v = java.lang.Double.doubleToRawLongBits(tmp);
      frame.pushLong(v);
    });
    MethodArea.registerMethod("java/lang/Double_longBitsToDouble_(J)D", frame -> {
      Long tmp = frame.popLong();
      double v = java.lang.Double.longBitsToDouble(tmp);
      frame.pushDouble(v);
    });

    MethodArea.registerMethod("java/lang/Double_valueOf_(D)Ljava/lang/Double;", frame -> {
      KClass clazz = MethodArea.findClass("java/lang/Double");
      Long kObject = clazz.newObject();
      Slot v2 = frame.popSlot();
      Slot v1 = frame.popSlot();
      Heap.load(kObject).setField("value", "D", new Slot[]{v1, v2});
      frame.pushRef(kObject);
    });
  }
}
