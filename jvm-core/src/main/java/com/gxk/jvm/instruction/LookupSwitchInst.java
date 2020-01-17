package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LookupSwitchInst implements Instruction {
  public final int offset;
  public final int def;
  public final int pairsCnt;
  public final byte[] bytes;

  public LookupSwitchInst(int offset, int def, int pairsCnt, byte[] bytes) {
    this.offset = offset;
    this.def = def;
    this.pairsCnt = pairsCnt;
    this.bytes = bytes;
  }

  @Override
  public int offset() {
    return this.offset;
  }

  @Override
  public void execute(Frame frame) {
    throw new UnsupportedOperationException("xxxxxxxxxxxxxx");
  }
}