package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class DAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object val = frame.popDouble();
    Integer index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    array.items[index] = val;
  }
}