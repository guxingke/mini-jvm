package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FLoad2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float tmp = frame.localVars.getFloat(2);
    frame.operandStack.pushFloat(tmp);
  }
}
