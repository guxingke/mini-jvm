package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IloadNInst implements Instruction {
  public final int n;

  public IloadNInst(int n) {
    this.n = n;
  }

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.localVars.getInt(n);
    frame.operandStack.pushInt(tmp);
  }
}
