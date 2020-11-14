package com.gxk.jvm.instruction.loads;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class LLoad2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.getLong(2);
    frame.pushLong(tmp);
  }

  @Override
  public String format() {
    return "lload2";
  }
}