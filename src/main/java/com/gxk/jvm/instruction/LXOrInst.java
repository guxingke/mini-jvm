package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LXOrInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long v2 = frame.operandStack.popLong();
    Long v1 = frame.operandStack.popLong();
    frame.operandStack.pushLong(v1 ^ v2);
  }
}