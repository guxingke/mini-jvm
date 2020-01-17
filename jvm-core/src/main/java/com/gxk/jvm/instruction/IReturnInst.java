package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.popInt();
    frame.thread.popFrame();
    if (!frame.thread.empty()) {
      frame.thread.currentFrame().pushInt(tmp);
    }
//    System.out.println("do ret " + tmp);
  }

  @Override
  public String format() {
    return "ireturn";
  }
}
