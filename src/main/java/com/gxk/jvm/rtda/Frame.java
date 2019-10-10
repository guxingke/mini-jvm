package com.gxk.jvm.rtda;

public class Frame {

  public final LocalVars localVars;
  public final OperandStack operandStack;
  public final Thread thread;
  public final Env env;
  public int nextPc;

  public Frame(int maxLocals, int maxStack, Thread thread, Env env) {
    this.localVars = new LocalVars(maxLocals);
    this.operandStack = new OperandStack(maxStack);
    this.thread = thread;
    this.env = env;
  }

  public void debug() {
    System.out.println("nextPc = " + nextPc);
    localVars.debug();
    System.out.println();
    operandStack.debug();
    System.out.println();
  }
}
