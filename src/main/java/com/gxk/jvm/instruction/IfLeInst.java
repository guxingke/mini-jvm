package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IfLeInst implements Instruction {
  public final int offset;

  public IfLeInst(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Integer val= frame.popInt();
    if (val <= 0) {
      frame.nextPc = frame.thread.getPc() + offset;
    }
  }
}
