package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IConst3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushInt(3);
  }

  @Override
  public String format() {
    return "iconst_3";
  }
}
