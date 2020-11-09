package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.UnionSlot;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.Field;
import com.gxk.jvm.rtda.heap.KObject;

public abstract class ExceptionBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/Exception_<init>_(Ljava/lang/String;)V", frame -> {
      KObject str = (KObject) frame.popRef();
      KObject thisObj = (KObject) frame.popRef();
      Field msgField = thisObj.getField("detailMessage", "Ljava/lang/String;");
      msgField.val = UnionSlot.of(str);
    });
  }
}
