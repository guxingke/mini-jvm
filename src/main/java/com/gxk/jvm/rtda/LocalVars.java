package com.gxk.jvm.rtda;

public class LocalVars {
  private final Slot[] slots;

  public LocalVars(int size) {
    this.slots = new Slot[size];
  }

  public void setInt(Integer index, Integer val) {
    slots[index] = new Slot(val);
  }

  public Integer getInt(Integer index) {
    return slots[index].num;
  }

  public void setFloat(Integer index, Float val) {
    int tmp = Float.floatToIntBits(val);
    slots[index] = new Slot(tmp);
  }

  public Float getFloat(Integer index) {
    Integer num = slots[index].num;
    return Float.intBitsToFloat(num);
  }

  public Long getLong(Integer index) {
    Integer low = slots[index].num;
    Integer high = slots[index + 1].num;

    long l1 = (high & 0x000000ffffffffL) << 32;
    long l2 = low & 0x00000000ffffffffL;
    return l1 | l2;
  }

  public void setLong(Integer index, Long val) {
    int low = (int) (val & 0x000000ffffffffL); //低32位
    int high = (int) (val >> 32); //高32位

    slots[index] = new Slot(low);
    slots[index + 1] = new Slot((high));
  }

  public void setDouble(int index, Double val) {
    long tmp = Double.doubleToLongBits(val);
    this.setLong(index, tmp);
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

  public void debug() {
    System.out.println("LocalVars: ");
    for (Slot slot : this.slots) {
      System.out.println("slot = " + slot);
    }
  }
}
