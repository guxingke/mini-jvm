package com.gxk.jvm.instruction.extended;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class IfNullInst implements Instruction {

  public final int offset;

  public IfNullInst(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Object ref = frame.popRef();
    if (ref == null) {
      frame.nextPc = frame.getPc() + offset;
    }
  }

  @Override
  public String format() {
    return "ifnull " + offset;
  }
}
