package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Iconst1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.operandStack.pushInt(1);
    debug(frame);
  }
}
