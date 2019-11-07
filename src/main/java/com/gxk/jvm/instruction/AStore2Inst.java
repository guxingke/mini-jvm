package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class AStore2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.popRef();
    frame.setRef(2, tmp);
  }
}
