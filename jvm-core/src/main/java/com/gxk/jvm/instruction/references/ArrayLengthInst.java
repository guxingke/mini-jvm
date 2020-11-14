package com.gxk.jvm.instruction.references;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.ArrayInstance;

public class ArrayLengthInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    ArrayInstance arr = (ArrayInstance) frame.popRef();
    frame.pushInt(arr.len);
  }

  @Override
  public String format() {
    return "arraylength";
  }
}
