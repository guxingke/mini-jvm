package com.gxk.jvm.rtda;

public class Frame {

  public final LocalVars localVars;
  public final OperandStack operandStack;
  public final Thread thread;
  public int nextPc;

  public Frame(int maxLocals, int maxStack, Thread thread) {
    this.localVars = new LocalVars(maxLocals);
    this.operandStack = new OperandStack(maxStack);
    this.thread = thread;
  }

  public void debug() {
    System.out.println("nextPc = " + nextPc);
    localVars.debug();
    System.out.println();
    operandStack.debug();
    System.out.println();
  }
}
