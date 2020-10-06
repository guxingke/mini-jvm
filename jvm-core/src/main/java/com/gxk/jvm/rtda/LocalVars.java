package com.gxk.jvm.rtda;

import com.gxk.jvm.rtda.heap.KObject;

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
    slots[index] = Slot.val(val);
  }

  public int getInt(int index) {
    return slots[index].val;
  }

  public void setFloat(int index, Float val) {
    int tmp = Float.floatToIntBits(val);
    slots[index] = Slot.val(tmp);
  }

  public float getFloat(int index) {
    int num = slots[index].val;
    return Float.intBitsToFloat(num);
  }

  public long getLong(int index) {
    int high = slots[index].val;
    int low = slots[index + 1].val;

    long l1 = (high & 0x000000ffffffffL) << 32;
    long l2 = low & 0x00000000ffffffffL;
    return l1 | l2;
  }

  public void setLong(int index, long val) {
    int high = (int) (val >> 32); //高32位
    int low = (int) (val & 0x000000ffffffffL); //低32位

    slots[index] = Slot.val(high);
    slots[index + 1] = Slot.val(low);
  }

  public void setDouble(int index, double val) {
    long tmp = Double.doubleToLongBits(val);

    int high = (int) (tmp >> 32); //高32位
    int low = (int) (tmp & 0x000000ffffffffL); //低32位

    slots[index] = Slot.val(high);
    slots[index + 1] = Slot.val(low);
  }

  public double getDouble(int index) {
    long tmp = this.getLong(index);
    return Double.longBitsToDouble(tmp);
  }

  public void setRef(int index, KObject ref) {
    slots[index] = Slot.ref(ref);
  }

  public KObject getRef(int index) {
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
      sb.append(space).append(String.format("%d | primitive | %s", i, slot.val)).append("\n");
    }
    return sb.append("\n").toString();
  }
}
