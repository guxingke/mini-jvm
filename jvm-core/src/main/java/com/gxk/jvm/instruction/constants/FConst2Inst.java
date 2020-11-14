package com.gxk.jvm.instruction.constants;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class FConst2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushFloat(2.0f);
  }
}
