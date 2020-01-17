package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class TableSwitchInst implements Instruction {
  public final int offset;
  public final int def;
  public final int low;
  public final int high;
  public final byte[] bytes;

  public TableSwitchInst(int offset, int def, int low, int high, byte[] bytes) {
    this.offset = offset;
    this.def = def;
    this.low = low;
    this.high = high;
    this.bytes = bytes;
  }

  @Override
  public int offset() {
    return this.offset;
  }

  @Override
  public void execute(Frame frame) {
    throw new UnsupportedOperationException("xxxxxxxxxxx");
  }
}