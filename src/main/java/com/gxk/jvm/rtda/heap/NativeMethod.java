package com.gxk.jvm.rtda.heap;

public interface NativeMethod {
  Object invoke(KObject obj, Object... args);
}
