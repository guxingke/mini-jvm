package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class AStore3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.popRef();
    frame.setRef(3, tmp);
  }
}
