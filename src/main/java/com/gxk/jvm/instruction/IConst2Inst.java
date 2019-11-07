package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IConst2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushInt(2);
  }
}
