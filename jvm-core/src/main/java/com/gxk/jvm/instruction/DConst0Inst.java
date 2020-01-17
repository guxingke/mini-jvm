package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class DConst0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushDouble(1.0d);
  }
}
