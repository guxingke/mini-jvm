package com.gxk.jvm.instruction.loads;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class ILoad0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int tmp = frame.getInt(0);
    frame.pushInt(tmp);
  }

  @Override
  public String format() {
    return "iload_0";
  }
}
