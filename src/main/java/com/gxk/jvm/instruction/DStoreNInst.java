package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class DStoreNInst implements Instruction {
  public final int n;

  public DStoreNInst(int n) {
    this.n = n;
  }

  @Override
  public void execute(Frame frame) {
    double tmp = frame.operandStack.popDouble();
    frame.localVars.setDouble(n, tmp);
  }
}
