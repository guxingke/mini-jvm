package com.gxk.jvm.rtda.heap;

import com.gxk.jvm.classpath.Entry;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * jvm heap
 */
@Data
public abstract class Heap {
  private static final Map<String, KMethod> methodRefMap;
  private static final Map<String, KClass> classRefMap;

  private static Entry defaultEntry;

  static {
    methodRefMap = new HashMap<>();
    classRefMap = new HashMap<>();
  }

  public static void registerMethod(String descriptor, KMethod method) {
    if (methodRefMap.containsKey(descriptor)) {
      throw new IllegalStateException();
    }
    methodRefMap.put(method.getName() + "_" + descriptor, method);
  }

  public static KMethod findMethod(String name, String descriptor) {
    return methodRefMap.get(name + "_" + descriptor);
  }

  public static KClass findClass(String name) {
    return classRefMap.get(name);
  }

  public static void registerClass(String name, KClass clazz) {
    classRefMap.putIfAbsent(name, clazz);
  }

  public static void setDefaultEntry(Entry entry) {
    defaultEntry = entry;
  }

  public static Entry getDefaultEntry() {
    return defaultEntry;
  }
}
