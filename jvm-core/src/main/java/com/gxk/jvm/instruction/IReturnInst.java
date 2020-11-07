package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.util.Utils;

public class IReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
//    Integer tmp = frame.popInt();
//    frame.thread.popFrame();
//    if (!frame.thread.empty()) {
//      frame.thread.currentFrame().pushInt(tmp);
//    }
//    System.out.println("do ret " + tmp);
    Utils.doReturn1();
  }

  @Override
  public String format() {
    return "ireturn";
  }
}
