package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FNegInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float tmp = frame.popFloat();
    frame.pushFloat(-tmp);
  }
}