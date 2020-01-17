package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LStoreNInst implements Instruction {
  public final int n;

  public LStoreNInst(int n) {
    this.n = n;
  }

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.popLong();
    frame.setLong(n, tmp);
  }
}
