package com.gxk.jvm.instruction.loads;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Instance;

public class ALoad1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Instance tmp = frame.getRef(1);
    frame.pushRef(tmp);
  }

  @Override
  public String format() {
    return "aload_1";
  }
}
