package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class DNegInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double tmp = frame.popDouble();
    frame.pushDouble(-tmp);
  }
}