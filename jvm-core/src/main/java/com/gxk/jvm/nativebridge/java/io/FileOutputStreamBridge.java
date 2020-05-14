package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.KField;
import com.gxk.jvm.rtda.memory.KObject;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class FileOutputStreamBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/io/FileOutputStream_open0_(Ljava/lang/String;Z)V", frame -> {
    });

    MethodArea.registerMethod("java/io/FileOutputStream_initIDs_()V", frame -> {
    });

    MethodArea.registerMethod("java/io/FileOutputStream_close0_()V", frame -> {
      // TODO real close
      frame.popRef();
    });

    MethodArea.registerMethod("java/io/FileDescriptor_sync_()V", frame -> {
      frame.popRef();
    });

    MethodArea.registerMethod("java/io/FileOutputStream_write_(IZ)V", frame -> {
      boolean append = frame.popInt() == 1;
      Integer val = frame.popInt();
      KObject thisObj = Heap.load(frame.popRef());
      KField fd = thisObj.getField("fd", "Ljava/io/FileDescriptor;");
      KObject fdObj = (KObject) Heap.load(fd.val[0].refOffset);
      Integer realFd = fdObj.getField("fd", "I").val[0].num;
      // out
      if (realFd == 1) {
        try {
          new FileOutputStream(FileDescriptor.out).write(val);
        } catch (IOException e) {
          throw new IllegalStateException();
        }
      }
      if (realFd == 2) {
        try {
          new FileOutputStream(FileDescriptor.err).write(val);
        } catch (IOException e) {
          throw new IllegalStateException();
        }
      }
    });

    MethodArea.registerMethod("java/io/FileOutputStream_writeBytes_([BIIZ)V", frame -> {
      boolean append = frame.popInt() == 1;
      Integer len= frame.popInt();
      Integer off= frame.popInt();
      KArray arg1 = (KArray) Heap.load(frame.popRef());

      byte[] bytes = new byte[len];
      for (int i = off; i < len; i++) {
        bytes[i - off] = (byte) Heap.load(arg1.items[i]).getField("value","B").val[0].num.intValue();
      }

      KObject thisObj = (KObject) Heap.load(frame.popRef());
      KField fd = thisObj.getField("fd", "Ljava/io/FileDescriptor;");
      KObject fdObj = (KObject) Heap.load(fd.val[0].refOffset);
      Integer realFd = fdObj.getField("fd", "I").val[0].num;
      // out
      if (realFd == 1) {
        try {
          new FileOutputStream(FileDescriptor.out).write(bytes);
        } catch (IOException e) {
          throw new IllegalStateException();
        }
      }
      if (realFd == 2) {
        try {
          new FileOutputStream(FileDescriptor.err).write(bytes);
        } catch (IOException e) {
          throw new IllegalStateException();
        }
      }
    });
  }
}
