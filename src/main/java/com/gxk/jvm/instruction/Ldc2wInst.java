package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Ldc2wInst implements Instruction {
  public final Long val;
  public final Double val2;

  @Override
  public int offset() {
    return 3;
  }

  public Ldc2wInst(Long val,Double val2) {
    this.val = val;
    this.val2 = val2;
  }

  @Override
  public void execute(Frame frame) {
    if (val != null) {
      frame.operandStack.pushLong(val);
    } else if (val2!= null) {
      frame.operandStack.pushDouble(val2);
    }
  }
}
