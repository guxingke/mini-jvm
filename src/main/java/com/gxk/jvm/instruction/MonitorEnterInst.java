package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class MonitorEnterInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    // TODO ...
    frame.popRef();
//    throw new UnsupportedOperationException();
  }
}
