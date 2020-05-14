package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.KField;

public class DALoadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer index = frame.popInt();
    KArray array = (KArray) Heap.load(frame.popRef());
    Long item = array.items[index];
    KField field = Heap.load(item).getField("value", "D");
    Slot[] slots = field.val;
    Integer high = slots[index].num;
    Integer low = slots[index + 1].num;

    long l1 = (high & 0x000000ffffffffL) << 32;
    long l2 = low & 0x00000000ffffffffL;
    long tmp = l1 | l2;
    frame.pushDouble(Double.longBitsToDouble(tmp));
  }
}