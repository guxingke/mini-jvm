package com.gxk.jvm.instruction.comparisons;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;

public class IfLtInst implements Instruction {
  public final int offset;

  public IfLtInst(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Integer val= frame.popInt();
    if (val < 0) {
      frame.nextPc = frame.getPc() + offset;
    }
  }
}
