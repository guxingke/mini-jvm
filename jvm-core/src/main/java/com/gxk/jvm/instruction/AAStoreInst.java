package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;

public class AAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long val = frame.popRef();
    Integer index = frame.popInt();
    KArray array = (KArray) Heap.load(frame.popRef());
    array.items[index] = val;
  }

  @Override
  public String format() {
    return "aastore";
  }
}