package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;


public class DMulInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double v2 = frame.operandStack.popDouble();
    double v1 = frame.operandStack.popDouble();
    frame.operandStack.pushDouble(v1 * v2);
  }
}