package com.gxk.jvm.rtda.memory;

import java.util.HashMap;
import java.util.Map;

public abstract class Heap {

  private static final Map<Long, KObject> objs = new HashMap<>();

  private static Long offset = 0L;

  public static Long allocate(KObject obj) {
    offset++;
    objs.put(offset, obj);
    return offset;
  }

  public static KObject load(Long offset) {
    KObject obj = objs.get(offset);
    if (obj == null) {
      throw new IllegalStateException("object not found");
    }
    return obj;
  }
}
