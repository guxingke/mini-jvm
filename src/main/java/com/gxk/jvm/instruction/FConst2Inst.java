package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FConst2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.operandStack.pushFloat(2.0f);
  }
}
