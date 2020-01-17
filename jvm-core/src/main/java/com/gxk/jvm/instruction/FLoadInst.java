package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FLoadInst implements Instruction {
  public final int index;

  public FLoadInst(int index) {
    this.index = index;
  }

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    float tmp = frame.getFloat(index);
    frame.pushFloat(tmp);
  }
}