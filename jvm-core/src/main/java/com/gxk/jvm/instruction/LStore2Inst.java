package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LStore2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.popLong();
    frame.setLong(2, tmp);
  }

  @Override
  public String format() {
    return "lstore_2";
  }
}