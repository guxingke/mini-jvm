package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;

public class BAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    byte val = frame.popInt().byteValue();
    Integer index = frame.popInt();
    KArray array = (KArray) Heap.load(frame.popRef());
    if (array.primitiveItems instanceof Boolean[]) {
      array.primitiveItems[index] = val != 0;
    } else {
      array.primitiveItems[index] = val;
    }
  }
}