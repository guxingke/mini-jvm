package com.gxk.jvm.nativebridge.java.util;

import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.util.Utils;

public abstract class PropertiesBridge {

  public static void registerNative0() {

    // mock
    Heap.registerMethod("java/util/Properties_<init>_()V", frame -> {
      frame.popRef();
    });
    // mock
    Heap.registerMethod("java/util/Properties_getProperty_(Ljava/lang/String;)Ljava/lang/String;", frame -> {
      KObject nameObj = (KObject) frame.popRef();
      frame.popRef();

      String val = System.getProperty(Utils.obj2Str(nameObj));
      if (val == null) {
        frame.pushRef(null);
        return;
      }
      frame.pushRef(Utils.str2Obj(val, frame.method.clazz.classLoader));
    });
  }
}
