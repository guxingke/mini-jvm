package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class MonitorExitInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    // TODO ...
    frame.popRef();
//    throw new UnsupportedOperationException();
  }
}
