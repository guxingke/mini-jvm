package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class AreturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.operandStack.popRef();
    frame.thread.popFrame();
    if (!frame.thread.empty()) {
      frame.thread.currentFrame().operandStack.pushRef(tmp);
    }
//    System.out.println("do ret " + tmp);
  }
}
