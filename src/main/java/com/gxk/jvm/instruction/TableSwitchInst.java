package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.Data;

@Data
public class TableSwitchInst implements Instruction {
  public final int offset;
  public final int def;
  public final int low;
  public final int high;
  public final byte[] bytes;

  @Override
  public int offset() {
    return this.offset;
  }

  @Override
  public void execute(Frame frame) {
    throw new UnsupportedOperationException("xxxxxxxxxxx");
  }
}