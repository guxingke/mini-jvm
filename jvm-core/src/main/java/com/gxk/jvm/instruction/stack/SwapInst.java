package com.gxk.jvm.instruction.stack;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Instance;

public class SwapInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object v2 = frame.popRef();
    Object v1 = frame.popRef();
    frame.pushRef((Instance) v2);
    frame.pushRef((Instance) v1);
  }
}
