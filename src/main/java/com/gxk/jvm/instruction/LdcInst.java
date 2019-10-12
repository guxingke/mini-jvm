package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LdcInst implements Instruction {
  public final Integer val;
  public final Object ref;

  @Override
  public int offset() {
    return 2;
  }

  public LdcInst(Integer val, Object ref) {
    this.val = val;
    this.ref = ref;
  }

  @Override
  public void execute(Frame frame) {
    if (val != null) {
      frame.operandStack.pushInt(val);
    } else if (ref != null) {
      frame.operandStack.pushRef(ref);
    }

  }
}
