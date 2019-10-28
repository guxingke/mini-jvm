package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvokeDynamicInst implements Instruction {

  public final int clazzRefIdx;
  public final int srRefIdx;
  public final int count;
  public final int zero;

  @Override
  public int offset() {
    return 5;
  }

  @Override
  public void execute(Frame frame) {
    throw new UnsupportedOperationException();
  }
}

