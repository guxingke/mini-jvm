package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Iload2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.localVars.getInt(2);
    frame.operandStack.pushInt(tmp);
    debug(frame);
  }
}
