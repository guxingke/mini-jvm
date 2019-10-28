package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class DReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double tmp = frame.operandStack.popDouble();
    frame.thread.popFrame();
    if (!frame.thread.empty()) {
      frame.thread.currentFrame().operandStack.pushDouble(tmp);
    }
//    System.out.println("do ret " + tmp);
  }
}