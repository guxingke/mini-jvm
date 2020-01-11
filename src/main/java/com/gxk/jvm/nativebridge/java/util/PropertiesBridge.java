package com.gxk.jvm.nativebridge.java.util;

import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.util.Utils;
import java.io.File;

public abstract class PropertiesBridge {

  public static void registerNative0() {

    // mock
    Heap.registerMethod("java/util/Properties_<init>_()V", frame -> {
      frame.popRef();
    });

    Heap.registerMethod("java/util/Properties_getProperty_(Ljava/lang/String;)Ljava/lang/String;", frame -> {
      KObject name = (KObject) frame.popRef();
      String jname = Utils.obj2Str(name);
      frame.popRef();

      if (jname.equals("line.separator")) {
        frame.pushRef(Utils.str2Obj(System.lineSeparator(), frame.method.clazz.classLoader));
        return;
      }
      if (jname.equals("sun.stdout.encoding")) {
        frame.pushRef(null);
        return;
      }
      if (jname.equals("sun.stdin.encoding")) {
        frame.pushRef(null);
        return;
      }
      throw new IllegalStateException();
    });
  }
}
