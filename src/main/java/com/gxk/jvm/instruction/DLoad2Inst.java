package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class DLoad2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double tmp = frame.getDouble(2);
    frame.pushDouble(tmp);
  }
}
