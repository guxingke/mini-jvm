package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class ArrayLengthInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    KArray kArray = (KArray) frame.operandStack.popRef();
    int length = kArray.items.length;
    frame.operandStack.pushInt(length);
  }
}
