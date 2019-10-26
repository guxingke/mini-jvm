package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FAddInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float a1 = frame.operandStack.popFloat();
    float a2 = frame.operandStack.popFloat();
    frame.operandStack.pushFloat(a1 + a2);
  }
}
