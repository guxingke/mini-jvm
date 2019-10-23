package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IfNullInst implements Instruction {
  public final int offset;

  public IfNullInst(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    throw new UnsupportedOperationException();
  }
}
