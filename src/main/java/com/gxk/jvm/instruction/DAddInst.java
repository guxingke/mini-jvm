package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class DAddInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double a1 = frame.operandStack.popDouble();
    double a2 = frame.operandStack.popDouble();
    frame.operandStack.pushDouble(a1 + a2);
  }
}
