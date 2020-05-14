package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;

public class ArrayLengthInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    KArray kArray = (KArray) Heap.load(frame.popRef());
    frame.pushInt(kArray.size);
  }

  @Override
  public String format() {
    return "arraylength";
  }
}
