package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KObject;

public class SwapInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object v2 = frame.popRef();
    Object v1 = frame.popRef();
    frame.pushRef((KObject) v2);
    frame.pushRef((KObject) v1);
  }
}
