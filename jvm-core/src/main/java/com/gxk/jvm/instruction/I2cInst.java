package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class I2cInst implements Instruction{

  @Override
  public int offset() {
    return 1;
  }

  @Override
  public void execute(Frame frame) {
    // do nothings
  }
}