package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FCmpLInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float v2 = frame.operandStack.popFloat();
    float v1 = frame.operandStack.popFloat();
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