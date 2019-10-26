package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IAndInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer a1 = frame.operandStack.popInt();
    Integer a2 = frame.operandStack.popInt();
    frame.operandStack.pushInt(a2 & a1);
  }
}