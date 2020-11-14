package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.Instance;

public class AALoadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    Object item = array.items[index];
    frame.pushRef((Instance) item);
  }

  @Override
  public String format() {
    return "aaload";
  }
}
