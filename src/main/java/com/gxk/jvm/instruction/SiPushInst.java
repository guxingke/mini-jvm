package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class SiPushInst implements Instruction {

  public final short val;

  public SiPushInst(short val) {
    this.val = val;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    frame.operandStack.pushInt((int) (this.val));
  }
}
