package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KArray;

public class DAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double val = frame.popDouble();
    int index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    ((double[]) array.items)[index] = val;
  }
}