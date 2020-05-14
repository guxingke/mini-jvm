package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Thread;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KObject;

public class AThrowInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Thread thread = frame.thread;
    Long offset = frame.popRef();
    KObject exc = Heap.load(offset);
    String name = exc.clazz.name;

    Integer handlerPc = thread.currentFrame().method.getHandlerPc(thread.getPc(), name);
    while (handlerPc == null && !thread.empty()) {
      Frame ef = thread.popFrame();
      String msg = ef.getCurrentMethodFullName() + "(" + ef.getCurrentSource() + ":" + ef.getCurrentLine() + ")";
      System.err.println(msg);
      if (thread.empty()) {
        break;
      }
      handlerPc = thread.currentFrame().method.getHandlerPc(thread.getPc(), name);
    }

    // no exception handler ...
    if (handlerPc == null) {
      System.err.println(exc);
      throw new RuntimeException("no exception handler");
    }

    thread.currentFrame().pushRef(offset);
    thread.currentFrame().nextPc = handlerPc;
  }

  @Override
  public String format() {
    return "athrow";
  }
}
