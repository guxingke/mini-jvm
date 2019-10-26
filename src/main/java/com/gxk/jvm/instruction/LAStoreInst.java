package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class LAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object val = frame.operandStack.popLong();
    Integer index = frame.operandStack.popInt();
    KArray array = (KArray) frame.operandStack.popRef();
    array.items[index] = val;
  }
}