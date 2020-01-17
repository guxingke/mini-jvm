package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class ArrayLengthInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    KArray kArray = (KArray) frame.popRef();
    int length = kArray.items.length;
    frame.pushInt(length);
  }

  @Override
  public String format() {
    return "arraylength";
  }
}
