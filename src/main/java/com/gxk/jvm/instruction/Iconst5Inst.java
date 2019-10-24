package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Iconst5Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.operandStack.pushInt(5);
  }
}
