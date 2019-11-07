package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IAddInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer a1 = frame.popInt();
    Integer a2 = frame.popInt();
    frame.pushInt(a1 + a2);
  }
}
