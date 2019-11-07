package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IStore0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.popInt();
    frame.setInt(0, tmp);
  }
}
