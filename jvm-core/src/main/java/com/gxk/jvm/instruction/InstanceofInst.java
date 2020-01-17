package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KObject;

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
    KObject obj = (KObject) frame.popRef();
    if (obj == null) {
      frame.pushInt(0);
      return;
    }
    boolean ret = obj.clazz.is(clazz);
    if (!ret) {
      frame.pushInt(0);
      return;
    }
    frame.pushInt(1);
  }
}