package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.Data;

@Data
public class ANewArrayInst implements Instruction{

  public final int index;

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    throw new UnsupportedOperationException(ANewArrayInst.class.getName());
  }
}
