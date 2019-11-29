package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class CheckcastInst implements Instruction {

  public final String clazz;

  public CheckcastInst(String clazz) {
    this.clazz = clazz;
  }

  @Override
  public void execute(Frame frame) {
//FIXME    throw new UnsupportedOperationException();
  }
}
