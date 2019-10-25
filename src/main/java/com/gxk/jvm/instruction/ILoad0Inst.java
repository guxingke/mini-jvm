package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class ILoad0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.localVars.getInt(0);
    frame.operandStack.pushInt(tmp);
  }
}
