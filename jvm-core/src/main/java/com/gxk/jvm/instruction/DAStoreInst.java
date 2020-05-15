package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;

public class DAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Double val = frame.popDouble();
    Integer index = frame.popInt();
    KArray array = ((KArray) Heap.load(frame.popRef()));
    array.primitiveItems[index] = val;
  }
}