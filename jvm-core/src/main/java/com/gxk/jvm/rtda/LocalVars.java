package com.gxk.jvm.rtda;

import com.gxk.jvm.rtda.heap.Instance;

public class LocalVars {

  private final Slot[] slots;

  public LocalVars(int size) {
    this.slots = new Slot[size];
  }

  public LocalVars(Slot[] slots) {
    this.slots = slots;
  }

  public Slot[] getSlots() {
    return this.slots;
  }

  public void setInt(int index, int val) {
    slots[index] = new Slot(val);
  }

  public int getInt(int index) {
    return slots[index].num;
  }

  public void setFloat(int index, float val) {
    int tmp = Float.floatToIntBits(val);
    slots[index] = new Slot(tmp);
  }

  public float getFloat(int index) {
    int num = slots[index].num;
    return Float.intBitsToFloat(num);
  }

  public long getLong(int index) {
    int high = slots[index].num;
    int low = slots[index + 1].num;

    long l1 = (high & 0x000000ffffffffL) << 32;
    long l2 = low & 0x00000000ffffffffL;
    return l1 | l2;
  }

  public void setLong(int index, long val) {
    int high = (int) (val >> 32); //高32位
    int low = (int) (val & 0x000000ffffffffL); //低32位

    slots[index] = new Slot(high);
    slots[index + 1] = new Slot(low);
  }

  public void setDouble(int index, double val) {
    long tmp = Double.doubleToLongBits(val);

    int high = (int) (tmp >> 32); //高32位
    int low = (int) (tmp & 0x000000ffffffffL); //低32位

    slots[index] = new Slot(high);
    slots[index + 1] = new Slot(low);
  }

  public double getDouble(int index) {
    long tmp = this.getLong(index);
    return Double.longBitsToDouble(tmp);
  }

  public void setRef(int index, Instance ref) {
    slots[index] = new Slot(ref);
  }

  public Instance getRef(int index) {
    return slots[index].ref;
  }

  public String debug(String space) {
    StringBuilder sb = new StringBuilder();
    sb.append(space).append(String.format("LocalVars: %d", this.slots.length)).append("\n");
    for (int i = 0; i < this.slots.length; i++) {
      Slot slot = this.slots[i];
      if (slot == null) {
        sb.append(space).append(String.format("%d | null | null", i)).append("\n");
        continue;
      }
      if (slot.ref != null) {
        sb.append(space).append(String.format("%d | ref       | %s", i, slot.ref)).append("\n");
        continue;
      }
      sb.append(space).append(String.format("%d | primitive | %s", i, slot.num)).append("\n");
    }
    return sb.append("\n").toString();
  }

  public void set(int i, Slot val) {
    this.slots[i] = val;
  }
}
