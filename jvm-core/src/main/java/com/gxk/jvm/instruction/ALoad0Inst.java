package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KObject;

public class ALoad0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.getRef(0);
    frame.pushRef((KObject) tmp);
  }

  @Override
  public String format() {
    return "aload_0";
  }
}
