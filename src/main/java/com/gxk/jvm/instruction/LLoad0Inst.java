package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LLoad0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.localVars.getLong(0);
    frame.operandStack.pushLong(tmp);
  }
}
