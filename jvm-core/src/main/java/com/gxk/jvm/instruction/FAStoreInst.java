package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.MethodArea;

public class FAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Float val = frame.popFloat();
    Integer index = frame.popInt();
    KArray array = (KArray) Heap.load(frame.popRef());
    Long offset = MethodArea.findClass("java.lang.Float").newObject();
    int tmp = Float.floatToIntBits(val);
    Heap.load(offset).setField("value", "F", new Slot[]{new Slot(tmp, Slot.FLOAT)});
    array.items[index] = offset;
  }
}