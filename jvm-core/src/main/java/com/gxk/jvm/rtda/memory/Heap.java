package com.gxk.jvm.rtda.memory;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.Thread;
import com.gxk.jvm.rtda.Threads;
import com.gxk.jvm.util.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    if (offset == null) {
      return null;
    }
    KObject obj = objs.get(offset);
    if (obj == null) {
      throw new IllegalStateException("object not found");
    }
    return obj;
  }

  public static void gc() {
    System.out.println("gc begin, " + objs.size());
    // mark
    // all static field
    for (KClass clz : MethodArea.getClasses()) {
      for (KField field : clz.getStaticFields()) {
        if (Utils.isReference(field)) {
          Utils.mark(field);
        }
      }
    }

    for (Thread thread : Threads.threads()) {
      Frame frame = thread.currentFrame();
      // local vars
      for (Slot slot : frame.getLocalVars().getSlots()) {
        Utils.mark(slot);
      }

      // stack
      for (Slot slot : frame.getOperandStack().getSlots()) {
        Utils.mark(slot);
      }
    }

    // sweep
    List<Long> rms = new ArrayList<>();
    objs.forEach((key, val) -> {
      if (!val.marked()) {
        rms.add(key);
      }
    });
    System.out.println("gc marked, " + rms.size() + " " + rms);
    rms.forEach(objs::remove);
    System.out.println("gc done, " + objs.size());
  }
}
