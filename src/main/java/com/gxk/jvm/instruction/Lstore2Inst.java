package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Lstore2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.operandStack.popLong();
    frame.localVars.setLong(2, tmp);
  }
}
