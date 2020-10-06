package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.MetaSpace;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KField;
import com.gxk.jvm.rtda.heap.KObject;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class FileOutputStreamBridge {

  public static void registerNatives0() {
    MetaSpace.registerMethod("java/io/FileOutputStream_open0_(Ljava/lang/String;Z)V", frame -> {
    });

    MetaSpace.registerMethod("java/io/FileOutputStream_initIDs_()V", frame -> {
    });

    MetaSpace.registerMethod("java/io/FileOutputStream_close0_()V", frame -> {
      // TODO real close
      frame.popRef();
    });

    MetaSpace.registerMethod("java/io/FileDescriptor_sync_()V", frame -> {
      frame.popRef();
    });

    MetaSpace.registerMethod("java/io/FileOutputStream_write_(IZ)V", frame -> {
      boolean append = frame.popInt() == 1;
      Integer val = frame.popInt();
      KObject thisObj = frame.popRef();
      KField fd = thisObj.getField("fd", "Ljava/io/FileDescriptor;");
      KObject fdObj = fd.val.getRef();
      int realFd = fdObj.getField("fd", "I").val.getInt();
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

    MetaSpace.registerMethod("java/io/FileOutputStream_writeBytes_([BIIZ)V", frame -> {
      boolean append = frame.popInt() == 1;
      int len= frame.popInt();
      int off= frame.popInt();
      KArray arg1 = (KArray) frame.popRef();

      byte[] bytes = new byte[len];
      for (int i = off; i < len; i++) {
        bytes[i - off] = ((byte[]) arg1.items)[i];
      }

      KObject thisObj = frame.popRef();
      KField fd = thisObj.getField("fd", "Ljava/io/FileDescriptor;");
      KObject fdObj = fd.val.getRef();
      int realFd = fdObj.getField("fd", "I").val.getInt();
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
