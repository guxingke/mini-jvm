package com.gxk.jvm.instruction.math;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class LAndInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    long a1 = frame.popLong();
    long a2 = frame.popLong();
    frame.pushLong(a2 & a1);
  }
}