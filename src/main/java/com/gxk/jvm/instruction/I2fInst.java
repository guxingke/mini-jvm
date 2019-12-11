package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class I2fInst implements Instruction{

  @Override
  public int offset() {
    return 1;
  }

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.popInt();
    frame.pushFloat(((float) tmp));
  }
}