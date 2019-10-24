package com.gxk.jvm.rtda.heap;

import com.gxk.jvm.rtda.Frame;

public interface NativeMethod {

  // do all things
  void invoke(Frame frame);
}
