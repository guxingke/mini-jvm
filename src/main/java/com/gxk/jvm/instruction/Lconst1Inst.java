package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Lconst1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushLong(1L);
  }
}
