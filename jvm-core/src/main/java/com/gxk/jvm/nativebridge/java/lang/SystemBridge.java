package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.KObject;
import com.gxk.jvm.util.Utils;

public abstract class SystemBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/lang/System_registerNatives_()V", (frame) -> {
    });

    MethodArea.registerMethod("java/lang/System_setIn0_(Ljava/io/InputStream;)V", (frame) -> {
    });
    MethodArea.registerMethod("java/lang/System_setOut0_(Ljava/io/PrintStream;)V", (frame) -> {
    });
    MethodArea.registerMethod("java/lang/System_setErr0_(Ljava/io/PrintStream;)V", (frame) -> {
    });
    MethodArea.registerMethod("java/lang/System_currentTimeMillis_()J", (frame) -> frame.pushLong(java.lang.System.currentTimeMillis()));
    MethodArea.registerMethod("java/lang/System_nanoTime_()J", (frame) -> frame.pushLong(java.lang.System.nanoTime()));
    MethodArea
        .registerMethod("java/lang/System_arraycopy_(Ljava/lang/Object;ILjava/lang/Object;II)V", (frame) -> {
      Integer len = frame.popInt();
      Integer dsp = frame.popInt();
      KArray dest = (KArray) Heap.load(frame.popRef());
      Integer ssp = frame.popInt();
      KArray source = (KArray) Heap.load(frame.popRef());

      for (int i = 0; i < len; i++) {
        dest.items[dsp++] = source.items[ssp++];
      }
    });
    MethodArea.registerMethod("java/lang/System_identityHashCode_(Ljava/lang/Object;)I", (frame) -> frame.pushInt(frame.popRef().hashCode()));
    MethodArea.registerMethod("java/lang/System_initProperties_(Ljava/util/Properties;)Ljava/util/Properties;", (frame) -> {
    });
    MethodArea
        .registerMethod("java/lang/System_mapLibraryName_(Ljava/lang/String;)Ljava/lang/String;", (frame) -> {
    });

    // hack
    MethodArea
        .registerMethod("java/lang/System_getenv_(Ljava/lang/String;)Ljava/lang/String;", frame -> {
      KObject nameObj = Heap.load(frame.popRef());

      String val = System.getenv(Utils.obj2Str(nameObj));
      if (val == null) {
        frame.pushRef(null);
        return;
      }
      frame.pushRef(Utils.str2Obj(val, frame.method.clazz.classLoader));
    });
  }
}
