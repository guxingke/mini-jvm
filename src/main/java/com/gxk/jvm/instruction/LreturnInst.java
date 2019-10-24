package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LreturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.operandStack.popLong();
    frame.thread.popFrame();
    if (!frame.thread.empty()) {
      frame.thread.currentFrame().operandStack.pushLong(tmp);
    }
//    System.out.println("do ret " + tmp);
  }
}
