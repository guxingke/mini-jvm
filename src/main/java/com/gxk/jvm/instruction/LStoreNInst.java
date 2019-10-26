package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LStoreNInst implements Instruction {
  public final int n;

  public LStoreNInst(int n) {
    this.n = n;
  }

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.operandStack.popLong();
    frame.localVars.setLong(n, tmp);
  }
}
