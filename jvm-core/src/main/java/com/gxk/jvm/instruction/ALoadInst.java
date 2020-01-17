package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class ALoadInst implements Instruction {
  public final int index;

  public ALoadInst(int index) {
    this.index = index;
  }

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.getRef(index);
    frame.pushRef(tmp);
  }

  @Override
  public String format() {
    return "aload " + index;
  }
}