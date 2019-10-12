package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class InvokespecialInst implements Instruction {

  public final int methodRefIndex;

  public InvokespecialInst(int methodRefIndex) {
    this.methodRefIndex = methodRefIndex;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    // 暂时只执行 sout

    Object obj = frame.operandStack.peekRef();
    if (obj != null) {
      System.out.println(frame.operandStack.popRef());
      return;
    }

    // TODO 暂时只支持 int
    System.out.println(frame.operandStack.popInt());
  }
}
