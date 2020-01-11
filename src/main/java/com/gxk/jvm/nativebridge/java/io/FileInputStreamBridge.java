package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KField;
import com.gxk.jvm.rtda.heap.KObject;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

public abstract class FileInputStreamBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/io/FileInputStream_open0_(Ljava/lang/String;Z)V", frame -> {
    });

    Heap.registerMethod("java/io/FileInputStream_initIDs_()V", frame -> {
    });

    Heap.registerMethod("java/io/FileInputStream_close0_()V", frame -> {
      // TODO real close
      frame.popRef();
    });
  }
}
