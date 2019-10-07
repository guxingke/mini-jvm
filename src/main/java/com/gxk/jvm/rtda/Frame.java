package com.gxk.jvm.rtda;

public class Frame {

  public final LocalVars localVars;
  public final OperandStack operandStack;

  public Frame(int maxLocals, int maxStack) {
    this.localVars = new LocalVars(maxLocals);
    this.operandStack = new OperandStack(maxStack);
  }
}
