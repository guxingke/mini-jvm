package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float tmp = frame.operandStack.popFloat();
    frame.thread.popFrame();
    if (!frame.thread.empty()) {
      frame.thread.currentFrame().operandStack.pushFloat(tmp);
    }
//    System.out.println("do ret " + tmp);
  }
}