package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.KObject;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.util.Utils;

public abstract class StringBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/lang/String_intern_()Ljava/lang/String;", frame -> {
    });

    MethodArea.registerMethod("java/lang/String_getBytes_()[B", frame -> {
      KObject obj = (KObject) Heap.load(frame.popRef());
      String str = Utils.obj2Str(obj);
      byte[] bytes = str.getBytes();
      Byte[] byteObj = new Byte[bytes.length];
      for (int i = 0; i < bytes.length; i++) {
        byteObj[i] = bytes[i];
      }
      Long arr = KArray.newArray(MethodArea.findClass("[B"), byteObj);
      frame.pushRef(arr);
    });
  }
}
