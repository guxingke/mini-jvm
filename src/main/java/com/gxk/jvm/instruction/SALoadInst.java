package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class SALoadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer index = frame.operandStack.popInt();
    KArray array = (KArray) frame.operandStack.popRef();
    int item = (int) array.items[index];
    frame.operandStack.pushInt(item);
  }
}