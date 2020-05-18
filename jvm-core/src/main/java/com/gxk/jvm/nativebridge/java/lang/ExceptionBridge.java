package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KObject;
import com.gxk.jvm.rtda.memory.MethodArea;

public abstract class ExceptionBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/lang/Exception_<init>_(Ljava/lang/String;)V", frame -> {
      Long str = frame.popRef();
      KObject thisObj = Heap.load(frame.popRef());
      thisObj.setField("detailMessage", "Ljava/lang/String;", new Slot[]{new Slot(str)});
    });
  }
}
