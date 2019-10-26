package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FSubInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float v2 = frame.operandStack.popFloat();
    float v1 = frame.operandStack.popFloat();
    frame.operandStack.pushFloat(v1 - v2);
  }
}
