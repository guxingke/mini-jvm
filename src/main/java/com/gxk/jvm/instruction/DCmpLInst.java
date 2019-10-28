package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class DCmpLInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double v2 = frame.operandStack.popDouble();
    double v1 = frame.operandStack.popDouble();
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