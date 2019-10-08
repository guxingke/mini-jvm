package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IreturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    debug(frame);
    System.out.println("do ret " + frame.operandStack.popInt());
  }
}
