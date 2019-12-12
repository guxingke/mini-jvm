package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IConst5Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushInt(5);
  }

  @Override
  public String format() {
    return "iconst_5";
  }
}
