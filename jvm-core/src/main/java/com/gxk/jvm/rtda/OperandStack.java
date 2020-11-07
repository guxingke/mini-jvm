package com.gxk.jvm.rtda;

import com.gxk.jvm.rtda.heap.KObject;

public class OperandStack {
  private final Stack<Slot> slots;

  public OperandStack(Integer size) {
    slots = new Stack<>(size);
  }

  public void pushInt(Integer val) {
    this.slots.push(new Slot(val));
  }

  public Integer popInt() {
    return this.slots.pop().num;
  }

  public void pushLong(Long val) {
    int low = (int) (val & 0x000000ffffffffL); //低32位
    int high = (int) (val >> 32); //高32位
    this.slots.push(new Slot(low));
    this.slots.push(new Slot(high));
  }

  public Long popLong() {
    Integer high = this.slots.pop().num;
    Integer low = this.slots.pop().num;

    long l1 = (high & 0x000000ffffffffL) << 32;
    long l2 = low & 0x00000000ffffffffL;
    return l1 | l2;
  }

  public void pushFloat(Float val) {
    int tmp = Float.floatToIntBits(val);
    this.slots.push(new Slot(tmp));
  }

  public Float popFloat() {
    Integer tmp = this.slots.pop().num;
    return Float.intBitsToFloat(tmp);
  }

  public void pushDouble(Double val) {
    long tmp = Double.doubleToLongBits(val);

    int low = (int) (tmp& 0x000000ffffffffL); //低32位
    int high = (int) (tmp>> 32); //高32位
    this.slots.push(new Slot(low));
    this.slots.push(new Slot(high));
  }

  public Double popDouble() {
    Long tmp = this.popLong();
    return Double.longBitsToDouble(tmp);
  }

  public void pushRef(KObject val) {
    this.slots.push(new Slot(val));
  }

  public Object popRef() {
    return this.slots.pop().ref;
  }

  public Slot popSlot() {
    return this.slots.pop();
  }

  public void pushSlot(Slot val) {
    this.slots.push(val);
  }

  public Stack<Slot> getSlots() {
    return this.slots;
  }

  public String debug(String space) {
    StringBuilder sb = new StringBuilder();
    sb.append(space).append(String.format("OperandStack: %d", this.slots.maxSize)).append("\n");
    for (int i = 0; i < this.slots.size(); i++) {
      Slot slot = this.slots.get(i);
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
}
