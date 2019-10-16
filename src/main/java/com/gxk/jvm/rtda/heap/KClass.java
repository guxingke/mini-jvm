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

  public KMethod getMethod(String name) {
    return methods.stream().filter(it -> Objects.equals(name, it.getName())).findFirst().get();
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
