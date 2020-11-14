package com.gxk.jvm.instruction.stores;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.PrimitiveArray;

public class LAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    long val = frame.popLong();
    int index = frame.popInt();
    final PrimitiveArray array = (PrimitiveArray) frame.popRef();
    array.longs[index] = val;
  }

  @Override
  public String format() {
    return "lastore";
  }
}