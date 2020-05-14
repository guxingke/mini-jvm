package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.MethodArea;
import sun.jvm.hotspot.memory.HeapBlock.Header;

public class SAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    short val = frame.popInt().shortValue();
    Integer index = frame.popInt();
    KArray array = (KArray) Heap.load(frame.popRef());
    Long offset = MethodArea.findClass("java.lang.Short").newObject();
    Heap.load(offset).setField("value", "S", new Slot[]{new Slot((int) val, Slot.SHORT)});

    array.items[index] = offset;
  }
}