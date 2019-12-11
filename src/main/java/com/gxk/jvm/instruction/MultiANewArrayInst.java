package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class MultiANewArrayInst implements Instruction{

  public final int index;
  public final int dimensions;

  public MultiANewArrayInst(int index, int dimensions) {
    this.index = index;
    this.dimensions = dimensions;
  }

  @Override
  public int offset() {
    return 4;
  }

  @Override
  public void execute(Frame frame) {
    throw new UnsupportedOperationException(MultiANewArrayInst.class.getName());
  }
}