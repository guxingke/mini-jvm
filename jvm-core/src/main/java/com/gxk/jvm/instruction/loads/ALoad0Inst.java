package com.gxk.jvm.instruction.loads;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Instance;

public class ALoad0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.getRef(0);
    frame.pushRef((Instance) tmp);
  }

  @Override
  public String format() {
    return "aload_0";
  }
}
