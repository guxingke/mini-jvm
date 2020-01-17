package com.gxk.jvm.nativebridge.java.lang;

import com.gxk.jvm.rtda.heap.Heap;

public abstract class ClassLoaderBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/ClassLoader_registerNatives_()V", frame -> {});

    Heap.registerMethod("java/lang/ClassLoader_defineClass0_(Ljava/lang/String;[BIILjava/security/ProtectionDomain;)Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/ClassLoader_defineClass1_(Ljava/lang/String;[BIILjava/security/ProtectionDomain;Ljava/lang/String;)Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/ClassLoader_defineClass2_(Ljava/lang/String;Ljava/nio/ByteBuffer;IILjava/security/ProtectionDomain;Ljava/lang/String;)Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/ClassLoader_resolveClass0_(Ljava/lang/Class;)V", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/ClassLoader_findBootstrapClass_(Ljava/lang/String;)Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/ClassLoader_findLoadedClass0_(Ljava/lang/String;)Ljava/lang/Class;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/ClassLoader_findBuiltinLib_(Ljava/lang/String;)Ljava/lang/String;", frame -> {
      throw new UnsupportedOperationException();
    });
    Heap.registerMethod("java/lang/ClassLoader_retrieveDirectives_()Ljava/lang/AssertionStatusDirectives;", frame -> {
      throw new UnsupportedOperationException();
    });
  }
}
