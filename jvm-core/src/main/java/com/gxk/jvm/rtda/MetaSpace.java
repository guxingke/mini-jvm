package com.gxk.jvm.rtda;

import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.NativeMethod;

public class MetaSpace {

  public static Thread main;

  public static Thread getMainEnv() {
    return main;
  }

  public static NativeMethod findNativeMethod(String key) {
    return Heap.findMethod(key);
  }
}
