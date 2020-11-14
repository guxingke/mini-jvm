package com.gxk.jvm.instruction.loads;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class ILoad2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.getInt(2);
    frame.pushInt(tmp);
  }

  @Override
  public String format() {
    return "iload_2";
  }
}
