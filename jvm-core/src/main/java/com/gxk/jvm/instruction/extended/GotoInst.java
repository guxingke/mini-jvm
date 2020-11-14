package com.gxk.jvm.instruction.extended;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class GotoInst implements Instruction {

  public final short offset;

  public GotoInst(short offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    frame.nextPc = frame.getPc() + offset;
  }

  @Override
  public String format() {
    return "goto " + offset;
  }
}
