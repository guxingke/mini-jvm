package com.gxk.jvm.instruction.loads;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Instance;

public class ALoad2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.getRef(2);
    frame.pushRef((Instance) tmp);
  }

  @Override
  public String format() {
    return "aload_2";
  }
}
