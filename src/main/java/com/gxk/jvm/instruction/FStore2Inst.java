package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FStore2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float tmp = frame.operandStack.popFloat();
    frame.localVars.setFloat(2, tmp);
  }
}
