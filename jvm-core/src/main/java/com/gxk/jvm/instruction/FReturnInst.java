package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.util.Utils;

public class FReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
//    float tmp = frame.popFloat();
//    frame.thread.popFrame();
//    if (!frame.thread.empty()) {
//      frame.thread.currentFrame().pushFloat(tmp);
//    }
//    System.out.println("do ret " + tmp);
    Utils.doReturn1();
  }
}