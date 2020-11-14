package com.gxk.jvm.instruction.math;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class INegInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int tmp = frame.popInt();
    frame.pushInt(-tmp);
  }
}