package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;

public class BAloadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer index = frame.popInt();
    KArray array = (KArray) Heap.load(frame.popRef());
    if (array.primitiveItems instanceof Boolean[]) {
      frame.pushInt(((Boolean) array.primitiveItems[index]) ? 1 : 0);
      return;
    }
    byte item = (byte) array.primitiveItems[index];
    frame.pushInt((int) item);
  }
}