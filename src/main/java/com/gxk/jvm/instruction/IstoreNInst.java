package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IstoreNInst implements Instruction {
  public final int n;

  public IstoreNInst(int n) {
    this.n = n;
  }

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.operandStack.popInt();
    frame.localVars.setInt(n, tmp);
  }
}
