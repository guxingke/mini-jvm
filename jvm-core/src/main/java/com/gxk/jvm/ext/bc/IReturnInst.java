package com.gxk.jvm.ext.bc;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;

public class IReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.popInt();
    frame.thread.popFrame();
    System.out.println(tmp);
  }

  @Override
  public String format() {
    return "ireturn";
  }
}
