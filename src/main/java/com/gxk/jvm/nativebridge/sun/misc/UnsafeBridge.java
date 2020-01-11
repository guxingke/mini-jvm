package com.gxk.jvm.nativebridge.sun.misc;

import com.gxk.jvm.rtda.heap.Heap;
import sun.misc.Unsafe;

public abstract class UnsafeBridge {

  public static void registerNatives0() {
    Heap.registerMethod("sun/misc/Unsafe_registerNatives_()V", frame -> {
    });
    Heap.registerMethod("sun/misc/Unsafe_getUnsafe_()Lsun/misc/Unsafe;", frame -> {
      frame.pushRef(null);
    });
    Heap.registerMethod("sun/misc/Unsafe_objectFieldOffset_(Ljava/lang/reflect/Field;)J", frame -> {
      frame.popRef();
      frame.popRef();
      frame.pushLong(1L);
    });

    Heap.registerMethod("sun/misc/Unsafe_allocateMemory_(J)J", frame -> {
      Long xx = frame.popLong();
      frame.popRef();
      frame.pushLong(xx);
    });
    Heap.registerMethod("sun/misc/Unsafe_putLong_(JJ)V", frame -> {
      frame.popLong();
      frame.popLong();
      frame.popRef();
    });
    Heap.registerMethod("sun/misc/Unsafe_getByte_(J)B", frame -> {
      Long x = frame.popLong();
      frame.popRef();
      int b = (x.byteValue());
      frame.pushInt(b);
    });
  }
}
