package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class ReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    // do nothing
    frame.thread.popFrame();
  }

  @Override
  public String format() {
    return "return";
  }
}
