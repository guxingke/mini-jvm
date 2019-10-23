package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LcmpInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long v2 = frame.operandStack.popLong();
    Long v1 = frame.operandStack.popLong();
    if (v1 == v2) {
      frame.operandStack.pushInt(0);
      return;
    }
    if (v1 < v2) {
      frame.operandStack.pushInt(-1);
      return;
    }
    frame.operandStack.pushInt(1);
  }
}
