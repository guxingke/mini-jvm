package com.gxk.jvm.rtda.heap;

import com.gxk.jvm.rtda.Slot;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KObject {

  public final List<KField> fields;
  public final KClass clazz;
  private KObject superObject;

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
    return this.superObject.getField(fieldName, fieldDescriptor);
  }

  public void setSuperObject(KObject superObject) {
    this.superObject = superObject;
  }

  public void setField(String name, String desc, Slot[] val) {
    KField field = this.getField(name, desc);
    field.val = val;
  }

  @Override
  public String toString() {
    return clazz.name + "@" + this.hashCode();
  }
}
