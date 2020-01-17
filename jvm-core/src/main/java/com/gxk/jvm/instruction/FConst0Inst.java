package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FConst0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushFloat(0.0f);
  }
}
