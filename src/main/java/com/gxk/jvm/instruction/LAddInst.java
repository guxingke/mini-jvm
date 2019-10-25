package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LAddInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long a1 = frame.operandStack.popLong();
    Long a2 = frame.operandStack.popLong();
    frame.operandStack.pushLong(a1 + a2);
  }
}
