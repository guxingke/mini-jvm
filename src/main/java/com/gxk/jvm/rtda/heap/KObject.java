package com.gxk.jvm.rtda.heap;

import java.util.List;
import java.util.Objects;

public class KObject {
  public final List<KMethod> methods;
  public final List<KField> fields;
  public final KClass clazz;

  public KObject(List<KMethod> methods, List<KField> fields, KClass clazz) {
    this.methods = methods;
    this.fields = fields;
    this.clazz = clazz;
  }

  public KField getField(String fieldName, String fieldDescriptor) {
    for (KField field : fields) {
      if (Objects.equals(field.name, fieldName) && Objects.equals(field.descriptor, fieldDescriptor)) {
        return field;
      }
    }
    return null;
  }
}
