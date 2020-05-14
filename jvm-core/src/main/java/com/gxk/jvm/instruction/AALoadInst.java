package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;

public class AALoadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer index = frame.popInt();
    KArray array = (KArray) Heap.load(frame.popRef());
    Long item = array.items[index];
    frame.pushRef(item);
  }

  @Override
  public String format() {
    return "aaload";
  }
}
