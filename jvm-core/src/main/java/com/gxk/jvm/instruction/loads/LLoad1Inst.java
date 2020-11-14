package com.gxk.jvm.instruction.loads;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class LLoad1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.getLong(1);
    frame.pushLong(tmp);
  }
}
