package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.Data;

@Data
public class MultiANewArrayInst implements Instruction{

  public final int index;
  public final int dimensions;

  @Override
  public int offset() {
    return 4;
  }

  @Override
  public void execute(Frame frame) {
    throw new UnsupportedOperationException(MultiANewArrayInst.class.getName());
  }
}