package com.gxk.jvm.rtda;

import com.gxk.jvm.rtda.heap.Instance;

public class OperandStack {

  //  private final Stack<Slot> slots;
  private final Slot[] slots;
  private int top;

  public OperandStack(Integer size) {
    slots = new Slot[size];
    top = 0;
  }

  public void push(Slot slot) {
    this.slots[top++] = slot;
  }

  public Slot pop() {
    top--;
    final Slot slot = this.slots[top];
    this.slots[top] = null; // gc
    return slot;
  }

  public void pushInt(int val) {
    this.slots[top++] = new Slot(val);
  }

  public int popInt() {
    return this.pop().num;
  }

  public void pushLong(long val) {
    int low = (int) (val & 0x000000ffffffffL); //低32位
    int high = (int) (val >> 32); //高32位
    this.push(new Slot(high));
    this.push(new Slot(low));
  }

  public long popLong() {
    int low = this.pop().num;
    int high = this.pop().num;

    long l1 = (high & 0x000000ffffffffL) << 32;
    long l2 = low & 0x00000000ffffffffL;
    return l1 | l2;
  }

  public void pushFloat(float val) {
    int tmp = Float.floatToIntBits(val);
    this.push(new Slot(tmp));
  }

  public float popFloat() {
    int tmp = this.pop().num;
    return Float.intBitsToFloat(tmp);
  }

  public void pushDouble(double val) {
    long tmp = Double.doubleToLongBits(val);

    int low = (int) (tmp & 0x000000ffffffffL); //低32位
    int high = (int) (tmp >> 32); //高32位
    this.push(new Slot(high));
    this.push(new Slot(low));
  }

  public double popDouble() {
    long tmp = this.popLong();
    return Double.longBitsToDouble(tmp);
  }

  public void pushRef(Instance val) {
    this.push(new Slot(val));
  }

  public Instance popRef() {
    return this.pop().ref;
  }

  public Slot popSlot() {
    return this.pop();
  }

  public void pushSlot(Slot val) {
    this.push(val);
  }

  public Slot[] getSlots() {
    return this.slots;
  }

  public String debug(String space) {
    StringBuilder sb = new StringBuilder();
    sb.append(space).append(String.format("OperandStack: %d", this.slots.length)).append("\n");
    for (int i = 0; i < this.slots.length; i++) {
      Slot slot = this.slots[i];
      if (slot == null) {
        sb.append(space).append(String.format("%d | null      | null", i)).append("\n");
        continue;
      }
      if (slot.ref != null) {
        sb.append(space).append(String.format("%d | ref       | %s", i, slot.ref)).append("\n");
        continue;
      }
      sb.append(space).append(String.format("%d | primitive | %s ", i, slot.num)).append("\n");
    }
    return sb.append("\n").toString();
  }

  public int getTop() {
    return top;
  }
}
