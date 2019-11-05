package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IUShrInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer v2 = frame.operandStack.popInt();
    Integer v1 = frame.operandStack.popInt();
    int s = v2 & 0x1f;

    if (v1 >= 0) {
      int ret = v1 >> s;
      frame.operandStack.pushInt(ret);
      return;
    }
    int ret = (v1 >> s) + (2 << ~s);
    frame.operandStack.pushInt(ret);
  }
}