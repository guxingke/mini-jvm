package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LLoad3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.getLong(3);
    frame.pushLong(tmp);
  }
}