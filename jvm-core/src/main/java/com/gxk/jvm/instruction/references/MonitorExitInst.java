package com.gxk.jvm.instruction.references;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;

public class MonitorExitInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    // TODO ...
    frame.popRef();
//    throw new UnsupportedOperationException();
  }
}
