package com.gxk.jvm.rtda.heap;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class KClass {
  public final String name;
  public final List<KMethod> methods;

  public KMethod getMainMethod() {
    for (KMethod method : methods) {
      if (Objects.equals("main", method.name)) {
        return method;
      }
    }
    return null;
  }
}
