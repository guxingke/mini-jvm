package com.gxk.jvm.instruction.loads;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class FLoad3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float tmp = frame.getFloat(3);
    frame.pushFloat(tmp);
  }
}