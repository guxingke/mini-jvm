package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KObject;

public abstract class SystemBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/System_registerNatives_()V", (frame) -> {
    });

    Heap.registerMethod("java/lang/System_setIn0_(Ljava/io/InputStream;)V", (frame) -> {
      KObject st = (KObject) frame.popRef();
      KClass cls = Heap.findClass("java/lang/System");
      cls.getField("in", "Ljava/io/InputStream;").val = new Slot[]{new Slot(st)};
    });
    Heap.registerMethod("java/lang/System_setOut0_(Ljava/io/PrintStream;)V", (frame) -> {
      KObject st = (KObject) frame.popRef();
      KClass cls = Heap.findClass("java/lang/System");
      cls.getField("out", "Ljava/io/PrintStream;").val = new Slot[]{new Slot(st)};
    });
    Heap.registerMethod("java/lang/System_setErr0_(Ljava/io/PrintStream;)V", (frame) -> {
      KObject st = (KObject) frame.popRef();
      KClass cls = Heap.findClass("java/lang/System");
      cls.getField("err", "Ljava/io/PrintStream;").val = new Slot[]{new Slot(st)};
    });
    Heap.registerMethod("java/lang/System_currentTimeMillis_()J", (frame) -> frame.pushLong(java.lang.System.currentTimeMillis()));
    Heap.registerMethod("java/lang/System_nanoTime_()J", (frame) -> frame.pushLong(java.lang.System.nanoTime()));
    Heap.registerMethod("java/lang/System_arraycopy_(Ljava/lang/Object;ILjava/lang/Object;II)V", (frame) -> {
      Integer len = frame.popInt();
      Integer dsp = frame.popInt();
      KArray dest = (KArray) frame.popRef();
      Integer ssp = frame.popInt();
      KArray source = (KArray) frame.popRef();

      for (int i = 0; i < len; i++) {
        dest.items[dsp++] = source.items[ssp++];
      }
    });
    Heap.registerMethod("java/lang/System_identityHashCode_(Ljava/lang/Object;)I", (frame) -> frame.pushInt(frame.popRef().hashCode()));
    Heap.registerMethod("java/lang/System_initProperties_(Ljava/util/Properties;)Ljava/util/Properties;", (frame) -> {
    });
    Heap.registerMethod("java/lang/System_mapLibraryName_(Ljava/lang/String;)Ljava/lang/String;", (frame) -> {
    });
  }
}
