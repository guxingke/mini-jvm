package com.gxk.jvm.instruction.loads;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Instance;
import com.gxk.jvm.rtda.heap.InstanceArray;

public class AALoadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int index = frame.popInt();
    InstanceArray array = (InstanceArray) frame.popRef();
    Object item = array.items[index];
    frame.pushRef((Instance) item);
  }

  @Override
  public String format() {
    return "aaload";
  }
}
