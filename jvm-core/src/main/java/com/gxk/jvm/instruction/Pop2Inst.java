package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class Pop2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.popRef();
    frame.popRef();
  }
}
