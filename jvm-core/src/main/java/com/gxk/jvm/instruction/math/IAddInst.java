package com.gxk.jvm.instruction.math;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class IAddInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer a1 = frame.popInt();
    Integer a2 = frame.popInt();
    frame.pushInt(a1 + a2);
  }

  @Override
  public String format() {
    return "iadd";
  }
}
