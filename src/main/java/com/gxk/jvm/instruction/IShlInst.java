package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IShlInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer v2 = frame.popInt();
    Integer v1 = frame.popInt();
    int s = v2 & 0x1f;
    int ret = v1 << s;
    frame.pushInt(ret);
  }
}