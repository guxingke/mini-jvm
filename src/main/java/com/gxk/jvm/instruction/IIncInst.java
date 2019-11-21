package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IIncInst implements Instruction {
  public final int index;
  public final int val;

  public IIncInst(int index, int val) {
    this.index = index;
    this.val = val;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.getInt(index);
    tmp += val;
    frame.setInt(index, tmp);
  }

  @Override
  public String format() {
    return String.format("%s %d %d", "iinc", index, val);
  }
}
