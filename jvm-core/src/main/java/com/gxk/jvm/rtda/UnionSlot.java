package com.gxk.jvm.rtda;

import com.gxk.jvm.rtda.heap.KObject;

public class UnionSlot {

  private Slot high;
  private Slot low;

  private UnionSlot(Slot high, Slot low) {
    this.high = high;
    this.low = low;
  }

  public static UnionSlot of(Slot high, Slot low) {
    return new UnionSlot(high, low);
  }

  public static UnionSlot of(Slot high) {
    return new UnionSlot(high, null);
  }

  // 初始化
  public static UnionSlot of(KObject instance) {
    return new UnionSlot(Slot.ref(instance), null);
  }

  public static UnionSlot of(int val) {
    return new UnionSlot(Slot.val(val), null);
  }

  public static UnionSlot of(float val) {
    return of(Float.floatToIntBits(val));
  }

  public static UnionSlot of(long val) {
    int high = (int) (val >> 32); //高32位
    int low = (int) (val & 0x000000ffffffffL); //低32位
    return new UnionSlot(Slot.val(high), Slot.val(low));
  }

  public static UnionSlot of(double val) {
    return of(Double.doubleToLongBits(val));
  }

  // 存
  public void setRef(KObject val) {
    high.ref = val;
  }

  public void setInt(int val) {
    high.val = val;
  }

  public void setFloat(float val) {
    setInt(Float.floatToIntBits(val));
  }

  public void setLong(long val) {
    int highV = (int) (val >> 32); //高32位
    int lowV = (int) (val & 0x000000ffffffffL); //低32位
    high.val = highV;
    low.val = lowV;
  }

  public void setDouble(double val) {
    setLong(Double.doubleToLongBits(val));
  }

  // 取
  public KObject getRef() {
    return high.ref;
  }

  public int getInt() {
    return high.val;
  }

  public float getFloat() {
    return Float.intBitsToFloat(getInt());
  }

  public long getLong() {
    final int high = this.high.val;
    final int low = this.low.val;
    long l1 = (high & 0x000000ffffffffL) << 32;
    long l2 = low & 0x00000000ffffffffL;
    return l1 | l2;
  }

  public double getDouble() {
    return Double.longBitsToDouble(getLong());
  }

  public void set(UnionSlot us) {
    this.high = us.high;
    this.low = us.low;
  }
}
