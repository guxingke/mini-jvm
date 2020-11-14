package com.gxk.jvm.instruction.loads;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.PrimitiveArray;

public class SALoadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    int index = frame.popInt();
    PrimitiveArray array = (PrimitiveArray) frame.popRef();
    frame.pushInt(array.ints[index]);
  }
}