package com.gxk.jvm.rtda;

import com.gxk.jvm.classfile.CodeFromByte;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.heap.KMethod;

import java.util.Map;

public class Frame {

  public final KMethod method;
  public final LocalVars localVars;
  public final OperandStack operandStack;
  public final Map<Integer, Instruction> instructionMap;
  public final Thread thread;
  public int nextPc;


  public Frame(KMethod method, Thread thread) {
    this.method = method;
    this.localVars = new LocalVars(method.getMaxLocals());
    this.operandStack = new OperandStack(method.getMaxStacks());
    this.thread = thread;
    this.instructionMap = method.getInstructionMap();
  }

  public void debug() {
    System.out.println("nextPc = " + nextPc);
    localVars.debug();
    System.out.println();
    operandStack.debug();
    System.out.println();
  }

  public Instruction getInst(int pc) {
    return this.instructionMap.get(pc);
  }
}
