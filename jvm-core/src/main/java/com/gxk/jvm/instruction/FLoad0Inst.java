package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FLoad0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float tmp = frame.getFloat(0);
    frame.pushFloat(tmp);
  }
}