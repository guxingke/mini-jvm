package com.gxk.jvm.instruction.constants;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class IConst2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushInt(2);
  }

  @Override
  public String format() {
    return "iconst_2";
  }
}
