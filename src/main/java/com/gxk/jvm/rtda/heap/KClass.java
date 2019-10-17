package com.gxk.jvm.rtda.heap;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class KClass {
  public final String name;
  public final List<KMethod> methods;
  public final List<KField> fields;

  private boolean staticInit = false;

  public KMethod getMainMethod() {
    for (KMethod method : methods) {
      if (Objects.equals("main", method.name)) {
        return method;
      }
    }
    return null;
  }

  public KMethod getMethod(String name, String descriptor) {
    for (KMethod  method: methods) {
      if (Objects.equals(method.name, name) && Objects.equals(method.descriptor, descriptor)) {
        return method;
      }
    }
    return null;
  }

  public KField getField(String fieldName, String fieldDescriptor) {
    for (KField field : fields) {
      if (Objects.equals(field.name, fieldName) && Objects.equals(field.descriptor, fieldDescriptor)) {
        return field;
      }
    }
    return null;
  }

  public KObject newObject() {
    return new KObject(methods, fields, this);
  }
}
