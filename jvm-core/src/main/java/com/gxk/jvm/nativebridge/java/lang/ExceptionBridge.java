package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.MetaSpace;
import com.gxk.jvm.rtda.UnionSlot;
import com.gxk.jvm.rtda.heap.KField;
import com.gxk.jvm.rtda.heap.KObject;

public abstract class ExceptionBridge {

  public static void registerNatives0() {
    MetaSpace.registerMethod("java/lang/Exception_<init>_(Ljava/lang/String;)V", frame -> {
      KObject str = frame.popRef();
      KObject thisObj = frame.popRef();
      KField msgField = thisObj.getField("detailMessage", "Ljava/lang/String;");
      msgField.val = UnionSlot.of(str);
    });
  }
}
