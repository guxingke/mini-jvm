package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LLoad1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.localVars.getLong(1);
    frame.operandStack.pushLong(tmp);
  }
}
