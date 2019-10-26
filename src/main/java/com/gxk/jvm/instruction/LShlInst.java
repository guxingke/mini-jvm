package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LShlInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer v2 = frame.operandStack.popInt();
    Long v1 = frame.operandStack.popLong();
    int s = v2 & 0x1f;
    long ret = v1 << s;
    frame.operandStack.pushLong(ret);
  }
}