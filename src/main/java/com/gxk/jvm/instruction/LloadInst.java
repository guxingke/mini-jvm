package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LloadInst implements Instruction {
  public final int index;

  public LloadInst(int index) {
    this.index = index;
  }

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.getLong(index);
    frame.pushLong(tmp);
  }
}
