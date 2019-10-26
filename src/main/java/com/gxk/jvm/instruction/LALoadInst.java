package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class LALoadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer index = frame.operandStack.popInt();
    KArray array = (KArray) frame.operandStack.popRef();
    long item = (Long) array.items[index];
    frame.operandStack.pushLong(item);
  }
}