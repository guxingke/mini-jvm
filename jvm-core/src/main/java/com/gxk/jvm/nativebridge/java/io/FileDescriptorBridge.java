package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.heap.Heap;

public abstract class FileDescriptorBridge {

  public static void registerNative0() {
    Heap.registerMethod("java/io/FileDescriptor_initIDs_()V", frame -> {
    });
  }
}
