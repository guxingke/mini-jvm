package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KMethod;

public class InvokestaticInst implements Instruction {

  public final String methodName;

  public InvokestaticInst(String methodName) {
    this.methodName = methodName;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    KMethod method = Heap.findMethod(methodName, "()I");
    Frame newFrame = new Frame(method, frame.thread);
    frame.thread.pushFrame(newFrame);
  }
}

