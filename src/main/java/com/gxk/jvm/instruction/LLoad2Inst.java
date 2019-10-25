package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LLoad2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.localVars.getLong(2);
    frame.operandStack.pushLong(tmp);
  }
}