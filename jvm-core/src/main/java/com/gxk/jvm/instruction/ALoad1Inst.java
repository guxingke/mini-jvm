package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Instance;

public class ALoad1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.getRef(1);
    frame.pushRef((Instance) tmp);
  }

  @Override
  public String format() {
    return "aload_1";
  }
}
