package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class L2fInst implements Instruction {

  @Override
  public int offset() {
    return 1;
  }

  @Override
  public void execute(Frame frame) {
    long tmp = frame.popLong();
    frame.pushFloat((float) tmp);
  }
}