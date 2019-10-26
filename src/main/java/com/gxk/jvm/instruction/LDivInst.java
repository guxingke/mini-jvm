package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LDivInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    long v2 = frame.operandStack.popLong();
    long v1 = frame.operandStack.popLong();
    frame.operandStack.pushLong(v1 / v2);
  }
}