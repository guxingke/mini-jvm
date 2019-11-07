package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IStore1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.popInt();
    frame.setInt(1, tmp);
  }
}
