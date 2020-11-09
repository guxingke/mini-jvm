package com.gxk.jvm.rtda;

import com.gxk.jvm.rtda.heap.KObject;

/**
 * 用于字段的联合 Slot
 */
public class UnionSlot {

  private Slot high;
  private Slot low;

  private UnionSlot(Slot high, Slot low) {
    this.high = high;
    this.low = low;
  }

  // 初始化
  public static UnionSlot of(Slot high, Slot low) {
    return new UnionSlot(high, low);
  }

  public static UnionSlot of(Slot high) {
    return new UnionSlot(high, null);
  }

  public static UnionSlot of(KObject instance) {
    return new UnionSlot(new Slot(instance), null);
  }

  public static UnionSlot of(int val) {
    return new UnionSlot(new Slot(val), null);
  }

  public static UnionSlot of(float val) {
    return of(Float.floatToIntBits(val));
  }

  public static UnionSlot of(long val) {
    int high = (int) (val >> 32); //高32位
    int low = (int) (val & 0x000000ffffffffL); //低32位
    return new UnionSlot(new Slot(high), new Slot(low));
  }

  public static UnionSlot of(double val) {
    return of(Double.doubleToLongBits(val));
  }

  // 存
  public void setRef(KObject val) {
    high.ref = val;
  }

  public void setInt(int val) {
    high.num = val;
  }

  public void setFloat(float val) {
    setInt(Float.floatToIntBits(val));
  }

  public void setLong(long val) {
    int highV = (int) (val >> 32); //高32位
    int lowV = (int) (val & 0x000000ffffffffL); //低32位
    high.num = highV;
    low.num = lowV;
  }

  public void setDouble(double val) {
    setLong(Double.doubleToLongBits(val));
  }

  public void set(UnionSlot neo) {
    this.high = neo.high;
    this.low = neo.low;
  }

  // 取
  public KObject getRef() {
    return high.ref;
  }

  public int getInt() {
    return high.num;
  }

  public float getFloat() {
    return Float.intBitsToFloat(getInt());
  }

  public long getLong() {
    final int high = this.high.num;
    final int low = this.low.num;
    long l1 = (high & 0x000000ffffffffL) << 32;
    long l2 = low & 0x00000000ffffffffL;
    return l1 | l2;
  }

  public double getDouble() {
    return Double.longBitsToDouble(getLong());
  }
}