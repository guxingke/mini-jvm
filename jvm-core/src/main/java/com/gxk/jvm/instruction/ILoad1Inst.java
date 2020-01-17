package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class ILoad1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.getInt(1);
    frame.pushInt(tmp);
  }

  @Override
  public String format() {
    return "iload_1";
  }
}
