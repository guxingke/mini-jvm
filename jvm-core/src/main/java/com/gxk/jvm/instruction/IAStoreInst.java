package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.MethodArea;

public class IAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer val = frame.popInt();
    Integer index = frame.popInt();
    KArray array = (KArray) Heap.load(frame.popRef());

    Long offset = MethodArea.findClass("java.lang.Integer").newObject();
    Heap.load(offset).setField("value", "I", new Slot[]{new Slot(val, Slot.INT)});
    array.items[index] = offset;
  }

  @Override
  public String format() {
    return "iastore";
  }
}