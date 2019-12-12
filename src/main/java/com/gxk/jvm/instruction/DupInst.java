package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class DupInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object obj = frame.popRef();
    frame.pushRef(obj);
    frame.pushRef(obj);
  }

  @Override
  public String format() {
    return "dup";
  }
}
