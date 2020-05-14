package com.gxk.jvm.nativebridge.java.util;

import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KObject;
import com.gxk.jvm.util.Utils;

public abstract class PropertiesBridge {

  public static void registerNative0() {

    // mock
    MethodArea.registerMethod("java/util/Properties_<init>_()V", frame -> {
      frame.popRef();
    });
    // mock
    MethodArea.registerMethod("java/util/Properties_getProperty_(Ljava/lang/String;)Ljava/lang/String;", frame -> {
      KObject nameObj = (KObject) Heap.load(frame.popRef());
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
