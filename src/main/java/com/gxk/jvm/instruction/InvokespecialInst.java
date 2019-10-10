package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class InvokespecialInst implements Instruction {

  public final int methodRefIndex;

  public InvokespecialInst(int methodRefIndex) {
    this.methodRefIndex = methodRefIndex;
  }

  @Override
  public void execute(Frame frame) {
    // 暂时只执行 sout
    System.out.println(frame.operandStack.popRef());
  }
}
