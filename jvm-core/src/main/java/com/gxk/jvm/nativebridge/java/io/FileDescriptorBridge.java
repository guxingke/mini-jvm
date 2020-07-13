package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.MetaSpace;

public abstract class FileDescriptorBridge {

  public static void registerNative0() {
    MetaSpace.registerMethod("java/io/FileDescriptor_initIDs_()V", frame -> {
    });
  }
}
