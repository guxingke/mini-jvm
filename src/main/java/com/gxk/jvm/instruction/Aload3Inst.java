package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Aload3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.localVars.getRef(3);
    frame.operandStack.pushRef(tmp);
  }
}
