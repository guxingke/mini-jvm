package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class SwapInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long v2 = frame.popRef();
    Long v1 = frame.popRef();
    frame.pushRef(v2);
    frame.pushRef(v1);
  }
}
