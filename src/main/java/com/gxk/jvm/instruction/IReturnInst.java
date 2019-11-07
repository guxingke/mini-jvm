package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.popInt();
    fame.thread.popFrame();
    if (!frame.thread.empty()) {
      frame.thread.currentFrame().pushInt(tmp);
    }
//    System.out.println("do ret " + tmp);
  }
}
