package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LloadInst implements Instruction {
  public final int index;

  public LloadInst(int index) {
    this.index = index;
  }

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.localVars.getLong(index);
    frame.operandStack.pushLong(tmp);
  }
}
