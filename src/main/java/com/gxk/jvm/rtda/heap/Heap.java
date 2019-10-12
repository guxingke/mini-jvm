package com.gxk.jvm.rtda.heap;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * jvm heap
 */
@Data
public class Heap {
  private final Map<String, KMethod> methodRefMap;
  private final Map<String, KClass> classRefMap;

  public Heap() {
    methodRefMap = new HashMap<>();
    classRefMap = new HashMap<>();
  }

  public void registerMethod(String descriptor, KMethod method) {
    if (this.methodRefMap.containsKey(descriptor)) {
      throw new IllegalStateException();
    }
    this.methodRefMap.put(descriptor, method);
  }

  public KMethod findMethod(String descriptor) {
    return this.methodRefMap.get(descriptor);
  }
}
