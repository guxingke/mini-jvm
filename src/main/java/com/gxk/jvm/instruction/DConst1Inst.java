package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class DConst1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.operandStack.pushDouble(0.0d);
  }
}
