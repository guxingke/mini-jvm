package com.gxk.jvm.rtda;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.heap.KMethod;

import java.util.Map;

public class Frame {

  public final KMethod method;
  private final LocalVars localVars;
  private final OperandStack operandStack;
  private final Map<Integer, Instruction> instructionMap;
  public final Thread thread;
  public int nextPc;

  private Runnable onPop;

  public Frame(KMethod method, Thread thread) {
    this.method = method;
    this.localVars = new LocalVars(method.getMaxLocals());
    this.operandStack = new OperandStack(method.getMaxStacks());
    this.thread = thread;
    this.instructionMap = method.getInstructionMap();
  }

  public Frame(KMethod method, LocalVars localVars, Thread thread) {
    this.method = method;
    this.localVars = localVars;
    this.operandStack = new OperandStack(method.getMaxStacks());
    this.thread = thread;
    this.instructionMap = method.getInstructionMap();
  }

  public void debug(String space) {
    System.out.println(space + "nextPc = " + nextPc);
    localVars.debug(space);
    System.out.println();
    operandStack.debug(space);
    System.out.println();
  }

  public Instruction getInst(int pc) {
    return this.instructionMap.get(pc);
  }

  public Runnable getOnPop() {
    return onPop;
  }

  public void setOnPop(Runnable onPop) {
    this.onPop = onPop;
  }

  // operand stack operation
  public void pushInt(Integer val) {
    this.operandStack.pushInt(val);
  }

  public Integer popInt() {
    return this.operandStack.popInt();
  }

  public void pushLong(Long val) {
    this.operandStack.pushLong(val);
  }

  public Long popLong() {
    return this.operandStack.popLong();
  }

  public void pushFloat(Float val) {
    this.operandStack.pushFloat(val);
  }

  public Float popFloat() {
    return this.operandStack.popFloat();
  }

  public void pushDouble(Double val) {
    this.operandStack.pushDouble(val);
  }

  public Double popDouble() {
    return this.operandStack.popDouble();
  }

  public void pushRef(Object val) {
    this.operandStack.pushRef(val);
  }

  public Object popRef() {
    return this.operandStack.popRef();
  }

  public Slot popSlot() {
    return this.operandStack.popSlot();
  }

  public void pushSlot(Slot val) {
    this.operandStack.pushSlot(val);
  }

  // local vars operation
  public void setInt(Integer index, Integer val) {
    this.localVars.setInt(index, val);
  }

  public Integer getInt(Integer index) {
    return this.localVars.getInt(index);
  }

  public void setFloat(Integer index, Float val) {
    this.localVars.setFloat(index, val);
  }

  public Float getFloat(Integer index) {
    return this.localVars.getFloat(index);
  }

  public Long getLong(Integer index) {
    return this.localVars.getLong(index);
  }

  public void setLong(Integer index, Long val) {
    this.localVars.setLong(index, val);
  }

  public void setDouble(int index, Double val) {
    this.localVars.setDouble(index, val);
  }

  public Double getDouble(int index) {
    return this.localVars.getDouble(index);
  }

  public void setRef(Integer index, Object ref) {
    this.localVars.setRef(index, ref);
  }

  public Object getRef(Integer index) {
    return this.localVars.getRef(index);
  }

  public LocalVars getLocalVars() {
    return this.localVars;
  }

  public OperandStack getOperandStack() {
    return this.operandStack;
  }

}
