package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class ALoad0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.localVars.getRef(0);
    frame.operandStack.pushRef(tmp);
  }
}
