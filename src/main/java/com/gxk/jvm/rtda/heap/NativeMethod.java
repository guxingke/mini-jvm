package com.gxk.jvm.rtda.heap;

public interface NativeMethod {
  Object invoke(Object... args);
}
