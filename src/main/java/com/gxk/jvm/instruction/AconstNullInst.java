package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class AconstNullInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.operandStack.pushRef(null);
  }
}
