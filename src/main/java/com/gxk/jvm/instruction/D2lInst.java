package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class D2lInst implements Instruction {

  @Override
  public int offset() {
    return 1;
  }

  @Override
  public void execute(Frame frame) {
    double tmp = frame.popDouble();
    frame.pushLong((long) tmp);
  }
}