package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.popLong();
    frame.thread.popFrame();
    if (!frame.thread.empty()) {
      frame.thread.currentFrame().pushLong(tmp);
    }
//    System.out.println("do ret " + tmp);
  }
}