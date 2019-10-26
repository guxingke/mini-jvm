package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LdcInst implements Instruction {
  public final String descriptor;
  public final Object val;

  @Override
  public int offset() {
    return 2;
  }

  public LdcInst(String descriptor, Object val) {
    this.descriptor = descriptor;
    this.val = val;
  }

  @Override
  public void execute(Frame frame) {
    switch (descriptor) {
      case "I":
        frame.operandStack.pushInt(((Integer) val));
        break;
      case "F":
        frame.operandStack.pushFloat(((float) val));
      default:
        frame.operandStack.pushRef(val);
    }
  }
}
