package com.gxk.jvm.instruction.stack;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class PopInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.popRef();
  }

  @Override
  public String format() {
    return "pop";
  }
}
