package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class ALoad1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.localVars.getRef(1);
    frame.operandStack.pushRef(tmp);
  }
}
