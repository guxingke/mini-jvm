package com.gxk.jvm.nativebridge.sun.misc;

import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KField;
import com.gxk.jvm.rtda.memory.KObject;
import com.gxk.jvm.rtda.memory.MethodArea;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public abstract class UnsafeBridge {

  private static Map<Long, byte[]> mem = new HashMap<>();
  private static Long next = 1L;

  public static void registerNatives0() {
    MethodArea.registerMethod("sun/misc/Unsafe_registerNatives_()V", frame -> {
    });
    MethodArea.registerMethod("sun/misc/Unsafe_getUnsafe_()Lsun/misc/Unsafe;", frame -> {
      frame.pushRef(null);
    });
    MethodArea
        .registerMethod("sun/misc/Unsafe_objectFieldOffset_(Ljava/lang/reflect/Field;)J", frame -> {
          frame.popRef();
          frame.popRef();
          frame.pushLong(1L);
        });
    MethodArea.registerMethod("sun/misc/Unsafe_getAndAddInt_(Ljava/lang/Object;JI)I", frame -> {
      Integer delta = frame.popInt();
      Long offset = frame.popLong();
      KObject obj = (KObject) Heap.load(frame.popRef());
      Object thisObj = frame.popRef();

      KField field = obj.getField("value", "I");
      Integer val = field.getVal()[0].num + delta;
      obj.setField("value", "I", new Slot[]{new Slot(val, Slot.INT)});
      frame.pushInt(val - delta);
    });

    MethodArea.registerMethod("sun/misc/Unsafe_allocateMemory_(J)J", frame -> {
      Long val = frame.popLong();
      frame.popRef();

      byte[] data = new byte[val.intValue()];
      mem.put(next, data);
      next += val.intValue();

      frame.pushLong(val);
    });

    MethodArea.registerMethod("sun/misc/Unsafe_putLong_(JJ)V", frame -> {
      Long v2 = frame.popLong();
      Long v1 = frame.popLong();
      frame.popRef(); // this

      ByteBuffer buffer = ByteBuffer.allocate(8);
      buffer.putLong(0, v2);
      byte[] bytes = buffer.array();
      mem.put(v1, bytes);
    });

    MethodArea.registerMethod("sun/misc/Unsafe_getByte_(J)B", frame -> {
      Long arg = frame.popLong();
      frame.popRef();

      byte[] bytes = mem.get(arg);
      byte b = bytes[0];
      frame.pushInt(((int) (b)));
    });
  }
}
