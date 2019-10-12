package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class IreturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.operandStack.popInt();
    frame.thread.popFrame();
    if (!frame.thread.empty()) {
      frame.thread.currentFrame().operandStack.pushInt(tmp);
    }
//    System.out.println("do ret " + tmp);
  }
}
