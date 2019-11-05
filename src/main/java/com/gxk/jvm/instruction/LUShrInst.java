package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LUShrInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer v2 = frame.operandStack.popInt();
    long v1 = frame.operandStack.popLong();
    int s = v2 & 0x3f;

    if (v1 >= 0) {
      long ret = v1 >> s;
      frame.operandStack.pushLong(ret);
      return;
    }
    long ret = (v1 >> s) + (2L << ~s);
    frame.operandStack.pushLong(ret);
  }
}