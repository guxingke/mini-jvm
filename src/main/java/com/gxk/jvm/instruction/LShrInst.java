package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LShrInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer v2 = frame.popInt();
    long v1 = frame.popInt();
    int s = v2 & 0x1f;
    long ret = v1 >> s;
    frame.pushLong(ret);
  }
}