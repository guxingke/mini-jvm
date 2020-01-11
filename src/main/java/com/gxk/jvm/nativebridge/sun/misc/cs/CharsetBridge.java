package com.gxk.jvm.nativebridge.sun.misc.cs;

import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.util.Utils;
import com.sun.prism.impl.FactoryResetException;

public abstract class CharsetBridge {

  public static void registerNatives0() {
    Heap.registerMethod("sun/nio/cs/StandardCharsets_<init>_()V", frame -> {
      frame.popRef();
    });
    Heap.registerMethod("java/nio/charset/Charset_defaultCharset_()Ljava/nio/charset/Charset;", frame -> {
      frame.pushRef(null);
    });
    Heap.registerMethod("java/nio/charset/Charset_forName_(Ljava/lang/String;)Ljava/nio/charset/Charset;", frame -> {
      frame.popRef();
      frame.pushRef(null);
    });
    Heap.registerMethod("java/nio/charset/Charset_name_()Ljava/lang/String;", frame -> {
      frame.popRef();
      frame.pushRef(Utils.str2Obj("UTF-8", frame.method.clazz.classLoader));
    });
    Heap.registerMethod("java/nio/charset/Charset_isSupported_(Ljava/lang/String;)Z",frame -> {
      frame.popRef();
      frame.pushInt(1);
    });
    Heap.registerMethod("java/nio/charset/Charset_newEncoder_()Ljava/nio/charset/CharsetEncoder;", frame -> {
      frame.popRef();
      frame.pushRef(null);
    });
    Heap.registerMethod("java/nio/charset/CharsetEncoder_onMalformedInput_(Ljava/nio/charset/CodingErrorAction;)Ljava/nio/charset/CharsetEncoder;", frame -> {
      frame.popRef();
      frame.popRef();
      frame.pushRef(null);
    });

    Heap.registerMethod("java/nio/charset/CharsetEncoder_onUnmappableCharacter_(Ljava/nio/charset/CodingErrorAction;)Ljava/nio/charset/CharsetEncoder;", frame -> {
      frame.popRef();
      frame.popRef();
      frame.pushRef(null);
    });

    Heap.registerMethod("java/nio/charset/CharsetEncoder_charset_()Ljava/nio/charset/Charset;", frame -> {
      frame.popRef();
      frame.pushRef(null);
    });
  }
}
