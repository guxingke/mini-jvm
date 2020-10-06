package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KObject;

public class SwapInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    KObject v2 = frame.popRef();
    KObject v1 = frame.popRef();
    frame.pushRef(v2);
    frame.pushRef(v1);
  }
}
