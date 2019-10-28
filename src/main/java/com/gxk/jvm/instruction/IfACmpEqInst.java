package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IfACmpEqInst implements Instruction {
  public final int offset;

  public IfACmpEqInst(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Object val2= frame.operandStack.popRef();
    Object val1= frame.operandStack.popRef();
    if (val1 == val2) {
      frame.nextPc = frame.thread.getPc() + offset;
    }
  }
}
