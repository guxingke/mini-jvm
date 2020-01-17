package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LStore0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.popLong();
    frame.setLong(0, tmp);
  }
}
