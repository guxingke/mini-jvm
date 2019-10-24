package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;


public class IMulInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer v2 = frame.operandStack.popInt();
    Integer v1 = frame.operandStack.popInt();
    frame.operandStack.pushInt(v1 * v2);
  }
}
