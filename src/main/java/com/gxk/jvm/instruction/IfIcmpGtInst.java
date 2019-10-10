package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IfIcmpGtInst implements Instruction {
  public final int offset;

  public IfIcmpGtInst(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Integer val2= frame.operandStack.popInt();
    Integer val1= frame.operandStack.popInt();
    if (val1 > val2) {
      frame.nextPc = offset;
    }
  }
}
