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
    this.methodRefMap.put(method.getName() + "_" + descriptor, method);
  }

  public KMethod findMethod(String name, String descriptor) {
    return this.methodRefMap.get(name + "_" + descriptor);
  }

  public KClass findClass(String name) {
    return this.classRefMap.get(name);
  }

  public void registerClass(String name, KClass clazz) {
    this.classRefMap.putIfAbsent(name, clazz);
  }
}
