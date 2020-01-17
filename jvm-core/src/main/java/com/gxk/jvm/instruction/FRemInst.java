package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FRemInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float v2 = frame.popFloat();
    float v1 = frame.popFloat();
    frame.pushFloat(v1 % v2);
  }
}