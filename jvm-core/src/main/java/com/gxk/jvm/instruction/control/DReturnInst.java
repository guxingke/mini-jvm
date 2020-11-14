package com.gxk.jvm.instruction.control;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.util.Utils;

public class DReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
//    double tmp = frame.popDouble();
//    frame.thread.popFrame();
//    if (!frame.thread.empty()) {
//      frame.thread.currentFrame().pushDouble(tmp);
//    }
//    System.out.println("do ret " + tmp);
    Utils.doReturn2();
  }
}