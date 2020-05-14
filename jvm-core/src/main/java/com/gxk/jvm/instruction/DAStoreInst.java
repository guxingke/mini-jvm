package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.MethodArea;

public class DAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Double val = frame.popDouble();
    Integer index = frame.popInt();
    KArray array = ((KArray) Heap.load(frame.popRef()));

    Long offset = MethodArea.findClass("java.lang.Double").newObject();
    Slot[] slots = new Slot[2];
    long tmp = Double.doubleToLongBits(val);
    int high = (int) (tmp >> 32); //高32位
    int low = (int) (tmp & 0x000000ffffffffL); //低32位
    slots[index] = new Slot(high, Slot.DOUBLE_HIGH);
    slots[index + 1] = new Slot(low, Slot.DOUBLE_LOW);
    Heap.load(offset).setField("value", "D", slots);
    array.items[index] = offset;
  }
}