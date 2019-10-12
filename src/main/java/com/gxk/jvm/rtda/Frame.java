package com.gxk.jvm.rtda;

import com.gxk.jvm.classfile.CodeFromByte;

public class Frame {

  public final LocalVars localVars;
  public final OperandStack operandStack;
  public final CodeFromByte code;
  public final Thread thread;
  public final Env env;
  public int nextPc;

  public Frame(int maxLocals, int maxStack, CodeFromByte code, Thread thread, Env env) {
    this.localVars = new LocalVars(maxLocals);
    this.operandStack = new OperandStack(maxStack);
    this.code = code;
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
