package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Istore2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.operandStack.popInt();
    frame.localVars.setInt(2, tmp);
    debug(frame);
  }
}
