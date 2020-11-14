package com.gxk.jvm.instruction.extended;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class WideInst implements Instruction {

  public final int offset;
  public final Instruction inst;

  public WideInst(int offset, Instruction inst) {
    this.offset = offset;
    this.inst = inst;
  }

  @Override
  public int offset() {
    return offset;
  }

  @Override
  public void execute(Frame frame) {
    inst.execute(frame);
  }
}
