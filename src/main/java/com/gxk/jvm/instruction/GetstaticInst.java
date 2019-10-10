package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class GetstaticInst implements Instruction {
  public final int FieldDerIndex;

  public GetstaticInst(int fieldDerIndex) {
    FieldDerIndex = fieldDerIndex;
  }


  @Override
  public void execute(Frame frame) {
    // TODO do nothings
  }
}
