package com.gxk.jvm.instruction.stores;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.PrimitiveArray;

public class SAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int  val = frame.popInt();
    int index = frame.popInt();
    final PrimitiveArray array = (PrimitiveArray) frame.popRef();
    array.ints[index] = val;
  }
}