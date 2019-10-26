package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FStoreNInst implements Instruction {
  public final int n;

  public FStoreNInst(int n) {
    this.n = n;
  }

  @Override
  public void execute(Frame frame) {
    Float tmp = frame.operandStack.popFloat();
    frame.localVars.setFloat(n, tmp);
  }
}
