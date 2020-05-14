package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.memory.MethodArea;

public abstract class FileDescriptorBridge {

  public static void registerNative0() {
    MethodArea.registerMethod("java/io/FileDescriptor_initIDs_()V", frame -> {
    });
  }
}
