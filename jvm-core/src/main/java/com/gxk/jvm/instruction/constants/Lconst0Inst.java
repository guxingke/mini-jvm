package com.gxk.jvm.instruction.constants;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class Lconst0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushLong(0L);
  }
}
