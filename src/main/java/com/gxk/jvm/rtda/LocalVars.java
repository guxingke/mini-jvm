package com.gxk.jvm.rtda;

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

  public void setInt(Integer index, Integer val) {
    slots[index] = new Slot(val, Slot.INT);
  }

  public Integer getInt(Integer index) {
    return slots[index].num;
  }

  public void setFloat(Integer index, Float val) {
    int tmp = Float.floatToIntBits(val);
    slots[index] = new Slot(tmp, Slot.FLOAT);
  }

  public Float getFloat(Integer index) {
    Integer num = slots[index].num;
    return Float.intBitsToFloat(num);
  }

  public Long getLong(Integer index) {
    Integer high = slots[index].num;
    Integer low = slots[index + 1].num;

    long l1 = (high & 0x000000ffffffffL) << 32;
    long l2 = low & 0x00000000ffffffffL;
    return l1 | l2;
  }

  public void setLong(Integer index, Long val) {
    int high = (int) (val >> 32); //高32位
    int low = (int) (val & 0x000000ffffffffL); //低32位

    slots[index] = new Slot(high, Slot.LONG_HIGH);
    slots[index + 1] = new Slot(low, Slot.LONG_LOW);
  }

  public void setDouble(int index, Double val) {
    long tmp = Double.doubleToLongBits(val);

    int high = (int) (tmp >> 32); //高32位
    int low = (int) (tmp & 0x000000ffffffffL); //低32位

    slots[index] = new Slot(high, Slot.DOUBLE_HIGH);
    slots[index + 1] = new Slot(low, Slot.DOUBLE_LOW);
  }

  public Double getDouble(int index) {
    Long tmp = this.getLong(index);
    return Double.longBitsToDouble(tmp);
  }

  public void setRef(Integer index, Object ref) {
    slots[index] = new Slot(ref);
  }

  public Object getRef(Integer index) {
    return slots[index].ref;
  }

  public void debug(String space) {
    System.out.println(space + "LocalVars: ");
    for (int i = 0; i < this.slots.length; i++) {
      Slot slot = this.slots[i];
      if (slot == null) {
        System.out.println(space + String.format("%d | null | null", i));
        continue;
      }
      if (slot.ref != null) {
        System.out.println(space + String.format("%d | null | %s", i, slot.ref));
        continue;
      }
      System.out.println(space + String.format("%d | %s | null", i, slot.num));
    }
  }
}
