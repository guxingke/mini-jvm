package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.MetaSpace;
import com.gxk.jvm.rtda.UnionSlot;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KObject;

public abstract class DoubleBridge {

  public static void registerNatives0() {
    MetaSpace.registerMethod("java/lang/Double_doubleToRawLongBits_(D)J", frame -> {
      double tmp = frame.popDouble();
      long v = java.lang.Double.doubleToRawLongBits(tmp);
      frame.pushLong(v);
    });
    MetaSpace.registerMethod("java/lang/Double_longBitsToDouble_(J)D", frame -> {
      Long tmp = frame.popLong();
      double v = java.lang.Double.longBitsToDouble(tmp);
      frame.pushDouble(v);
    });

    MetaSpace.registerMethod("java/lang/Double_valueOf_(D)Ljava/lang/Double;", frame -> {
      KClass clazz = MetaSpace.findClass("java/lang/Double");
      KObject kObject = clazz.newObject();
      double val = frame.popDouble();
      kObject.setField("value", "D", UnionSlot.of(val));
      frame.pushRef(kObject);
    });
  }
}
