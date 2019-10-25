package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IStore1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.operandStack.popInt();
    frame.localVars.setInt(1, tmp);
  }
}
