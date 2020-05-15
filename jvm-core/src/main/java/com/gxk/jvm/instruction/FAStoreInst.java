package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;

public class FAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Float val = frame.popFloat();
    Integer index = frame.popInt();
    KArray array = (KArray) Heap.load(frame.popRef());
    array.primitiveItems[index] = val;
  }
}