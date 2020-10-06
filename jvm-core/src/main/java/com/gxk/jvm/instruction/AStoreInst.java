package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KObject;

public class AStoreInst implements Instruction {

  public final int index;

  public AStoreInst(int index) {
    this.index = index;
  }

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    KObject tmp = frame.popRef();
    frame.setRef(index, tmp);
  }

  @Override
  public String format() {
    return "astore " + index;
  }

}