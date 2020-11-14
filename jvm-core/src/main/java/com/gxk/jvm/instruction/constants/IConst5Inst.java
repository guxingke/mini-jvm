package com.gxk.jvm.instruction.constants;

import com.gxk.jvm.instruction.Instruction;


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
