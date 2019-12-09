package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.KObject;

public class AThrowInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer pc = frame.thread.getPc();
    KObject exc = (KObject) frame.popRef();
    String name = exc.clazz.name;

    frame.pushRef(exc);
    Integer handlerPc = frame.method.getHandlerPc(pc, name);
    if (handlerPc == null) {
      throw new UnsupportedOperationException();
    }

    frame.nextPc = handlerPc;
  }
}
