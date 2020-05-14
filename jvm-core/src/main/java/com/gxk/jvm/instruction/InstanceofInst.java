package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KObject;

public class InstanceofInst implements Instruction {

  public final String clazz;

  public InstanceofInst(String clazz) {
    this.clazz = clazz;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.popRef();
    if (tmp == null || tmp == -1L) {
      frame.pushInt(0);
      return;
    }
    boolean ret = Heap.load(tmp).clazz.is(clazz);
    if (!ret) {
      frame.pushInt(0);
      return;
    }
    frame.pushInt(1);
  }
}