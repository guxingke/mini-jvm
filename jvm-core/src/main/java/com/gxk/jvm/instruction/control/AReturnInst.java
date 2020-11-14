package com.gxk.jvm.instruction.control;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.util.Utils;

public class AReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
//    Object tmp = frame.popRef();
//    frame.thread.popFrame();
//    if (!frame.thread.empty()) {
//      frame.thread.currentFrame().pushRef(tmp);
//    }
//    System.out.println("do ret " + tmp);
    Utils.doReturn1();
  }

  @Override
  public String format() {
    return "areturn";
  }
}
