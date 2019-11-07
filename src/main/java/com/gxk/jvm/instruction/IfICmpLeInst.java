package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IfICmpLeInst implements Instruction {
  public final int offset;

  public IfICmpLeInst(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Integer val2= frame.popInt();
    Integer val1= frame.popInt();
    if (val1 <= val2) {
      frame.nextPc = frame.thread.getPc() + offset;
    }
  }
}
