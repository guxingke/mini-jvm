package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.MetaSpace;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.util.Utils;

public abstract class StringBridge {

  public static void registerNatives0() {
    MetaSpace.registerMethod("java/lang/String_intern_()Ljava/lang/String;", frame -> {
    });

    MetaSpace.registerMethod("java/lang/String_getBytes_()[B", frame -> {
      KObject obj = frame.popRef();
      String str = Utils.obj2Str(obj);
      byte[] bytes = str.getBytes();
      byte[] byteObj = new byte[bytes.length];
      for (int i = 0; i < bytes.length; i++) {
        byteObj[i] = bytes[i];
      }
      KArray arr = new KArray(MetaSpace.findClass("[B"), byteObj, byteObj.length);

      frame.pushRef(arr);
    });
  }
}
