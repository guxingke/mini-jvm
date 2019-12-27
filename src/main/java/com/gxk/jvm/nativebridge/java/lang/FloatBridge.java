package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.heap.Heap;

public abstract class FloatBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/Float_intBitsToFloat_(I)F", frame -> {
      java.lang.Integer tmp = frame.popInt();
      float v = java.lang.Float.intBitsToFloat(tmp);
      frame.pushFloat(v);
    });
    Heap.registerMethod("java/lang/Float_floatToRawIntBits_(F)I", frame -> {
      float tmp = frame.popFloat();
      int v = java.lang.Float.floatToRawIntBits(tmp);
      frame.pushInt(v);
    });
  }
}
