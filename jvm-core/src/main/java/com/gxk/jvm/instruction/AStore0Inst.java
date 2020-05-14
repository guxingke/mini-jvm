package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class AStore0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.popRef();
    frame.setRef(0, tmp);
  }

  @Override
  public String format() {
    return "astore_0";
  }
}
