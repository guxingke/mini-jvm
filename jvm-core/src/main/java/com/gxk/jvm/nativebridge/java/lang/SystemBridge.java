package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.MetaSpace;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.util.Utils;

public abstract class SystemBridge {

  public static void registerNatives0() {
    MetaSpace.registerMethod("java/lang/System_registerNatives_()V", (frame) -> {
    });

    MetaSpace.registerMethod("java/lang/System_setIn0_(Ljava/io/InputStream;)V", (frame) -> {
    });
    MetaSpace.registerMethod("java/lang/System_setOut0_(Ljava/io/PrintStream;)V", (frame) -> {
    });
    MetaSpace.registerMethod("java/lang/System_setErr0_(Ljava/io/PrintStream;)V", (frame) -> {
    });
    MetaSpace.registerMethod("java/lang/System_currentTimeMillis_()J", (frame) -> frame.pushLong(java.lang.System.currentTimeMillis()));
    MetaSpace.registerMethod("java/lang/System_nanoTime_()J", (frame) -> frame.pushLong(java.lang.System.nanoTime()));
    MetaSpace.registerMethod("java/lang/System_arraycopy_(Ljava/lang/Object;ILjava/lang/Object;II)V", (frame) -> {
      Integer len = frame.popInt();
      Integer dsp = frame.popInt();
      KArray dest = (KArray) frame.popRef();
      Integer ssp = frame.popInt();
      KArray source = (KArray) frame.popRef();

      for (int i = 0; i < len; i++) {
        dest.items[dsp++] = source.items[ssp++];
      }
    });
    MetaSpace.registerMethod("java/lang/System_identityHashCode_(Ljava/lang/Object;)I", (frame) -> frame.pushInt(frame.popRef().hashCode()));
    MetaSpace.registerMethod("java/lang/System_initProperties_(Ljava/util/Properties;)Ljava/util/Properties;", (frame) -> {
    });
    MetaSpace.registerMethod("java/lang/System_mapLibraryName_(Ljava/lang/String;)Ljava/lang/String;", (frame) -> {
    });

    // hack
    MetaSpace.registerMethod("java/lang/System_getenv_(Ljava/lang/String;)Ljava/lang/String;", frame -> {
      KObject nameObj = (KObject) frame.popRef();

      String val = System.getenv(Utils.obj2Str(nameObj));
      if (val == null) {
        frame.pushRef(null);
        return;
      }
      frame.pushRef(Utils.str2Obj(val, frame.method.clazz.classLoader));
    });
  }
}
