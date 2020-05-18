package com.gxk.jvm.rtda.memory;

import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.util.Logger;
import com.gxk.jvm.util.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KObject implements Cloneable {

  public final List<KField> fields;
  public final KClass clazz;
  private Long superObject;

  // for class obj
  private KClass metaClass;

  // extra
  private Object extra;

  // reference cnt
  private Integer refCnt = 0;
  private Long address;

  public KObject(KClass clazz) {
    fields = new ArrayList<>();
    this.clazz = clazz;
  }

  public KObject(List<KField> fields, KClass clazz) {
    this.fields = fields;
    this.clazz = clazz;
  }

  public KField getField(String fieldName, String fieldDescriptor) {
    // this object
    for (KField field : fields) {
      if (Objects.equals(field.name, fieldName) && Objects.equals(field.descriptor, fieldDescriptor)) {
        return field;
      }
    }

    if (this.superObject == null) {
      return null;
    }

    // super object
    return Heap.load(this.superObject).getField(fieldName, fieldDescriptor);
  }

  public void setSuperObject(Long superObject) {
    this.superObject = superObject;
  }

  public void setField(String name, String desc, Slot[] val) {
    KField field = this.getField(name, desc);
    field.setVal(val);
    Utils.incRefCnt(val);
  }

  public Long clone() throws CloneNotSupportedException {
    KObject obj = ((KObject) super.clone());
    Long offset = Heap.allocate(obj);
    return offset;
  }

  @Override
  public String toString() {
    return clazz.name + "@" + this.hashCode();
  }

  public KClass getMetaClass() {
    return metaClass;
  }

  public void setMetaClass(KClass metaClass) {
    this.metaClass = metaClass;
  }

  public Object getExtra() {
    return extra;
  }

  public void setExtra(Object extra) {
    this.extra = extra;
  }

  public Integer incRefCnt() {
    this.refCnt++;

    for (KField field : this.fields) {
      field.incRefCnt();
    }

    return this.refCnt;
  }

  public Integer decRefCnt() {
    if (refCnt == 0) {
      throw new IllegalStateException();
    }
    this.refCnt--;

    for (KField field : this.fields) {
      field.decRefCnt();
    }

    if (refCnt == 0) {
      Logger.error("this object should be release " + this.hashCode());
    }
    return this.refCnt;
  }

  public void setAddress(Long address) {
    this.address = address;
  }

  public Long getAddress() {
    return this.address;
  }
}
