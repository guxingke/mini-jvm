package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Goto1Inst implements Instruction {

  public final short offset;

  public Goto1Inst(short offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    frame.nextPc = frame.thread.getPc() + offset;
  }
}
