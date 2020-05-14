package com.gxk.jvm.nativebridge.java.util;

import com.gxk.jvm.rtda.memory.MethodArea;

public abstract class RandomBridge {

  public static void registerNatives0() {
    // random
    MethodArea.registerMethod("java/util/Random_<clinit>_()V", frame -> {
    });
    MethodArea.registerMethod("java/util/Random_<init>_()V", frame -> {
      frame.popRef();
    });
    MethodArea.registerMethod("java/util/Random_<init>_(J)V", frame -> {
      frame.popLong();
      frame.popRef();
    });
    MethodArea.registerMethod("java/util/Random_nextInt_()I", frame -> {
      int tmp = new java.util.Random().nextInt();
      frame.popRef();
      frame.pushInt(tmp);
    });
    MethodArea.registerMethod("java/util/Random_nextInt_(I)I", frame -> {
      Integer rg = frame.popInt();
      frame.popRef();
      int tmp = new java.util.Random().nextInt(rg);
      frame.pushInt(tmp);
    });
  }
}
