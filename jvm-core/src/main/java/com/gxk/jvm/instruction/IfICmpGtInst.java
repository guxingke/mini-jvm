package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IfICmpGtInst implements Instruction {

  public final int offset;

  public IfICmpGtInst(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Integer val2 = frame.popInt();
    Integer val1 = frame.popInt();
    if (val1 > val2) {
      frame.nextPc = frame.thread.getPc() + offset;
    }
  }

  @Override
  public String format() {
    return "if_icmpgt " + offset;
  }
}
