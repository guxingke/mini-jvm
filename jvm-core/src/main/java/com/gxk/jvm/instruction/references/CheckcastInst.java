package com.gxk.jvm.instruction.references;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;

public class CheckcastInst implements Instruction {

  public final String clazz;

  public CheckcastInst(String clazz) {
    this.clazz = clazz;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
//FIXME    throw new UnsupportedOperationException();
  }

  @Override
  public String format() {
    return "checkcast " + clazz;
  }
}
