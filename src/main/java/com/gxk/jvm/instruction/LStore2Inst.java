package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LStore2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.operandStack.popLong();
    frame.localVars.setLong(3, tmp);
  }
}