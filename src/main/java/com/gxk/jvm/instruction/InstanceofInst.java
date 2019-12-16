package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class InstanceofInst implements Instruction {

  public final String clazz;

  public InstanceofInst(String clazz) {
    this.clazz = clazz;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    throw new UnsupportedOperationException("InstanceofInst");
  }
}