package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.Instance;
import com.gxk.jvm.rtda.heap.PrimitiveArray;
import com.gxk.jvm.util.Utils;

public abstract class StringBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/String_intern_()Ljava/lang/String;", frame -> {
    });

    Heap.registerMethod("java/lang/String_getBytes_()[B", frame -> {
      Instance obj = frame.popRef();
      String str = Utils.obj2Str(obj);
      byte[] bytes = str.getBytes();

      final PrimitiveArray array = PrimitiveArray.byteArray(bytes.length);
      for (int i = 0; i < bytes.length; i++) {
        array.ints[i] = bytes[i];
      }
      frame.pushRef(array);
    });
  }
}
