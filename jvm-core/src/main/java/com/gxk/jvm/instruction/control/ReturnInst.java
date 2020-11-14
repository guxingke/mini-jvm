package com.gxk.jvm.instruction.control;

import com.gxk.jvm.instruction.Instruction;


import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.util.Utils;

public class ReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    // do nothing
//    frame.thread.popFrame();
    Utils.doReturn0();
  }

  @Override
  public String format() {
    return "return";
  }
}
