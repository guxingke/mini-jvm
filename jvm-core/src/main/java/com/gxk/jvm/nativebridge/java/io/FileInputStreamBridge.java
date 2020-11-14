package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.Instance;
import com.gxk.jvm.util.Utils;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class FileInputStreamBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/io/FileInputStream_open0_(Ljava/lang/String;)V", frame -> {
      Instance fileName = (Instance) frame.popRef();
      Instance thisObj = (Instance) frame.popRef();

      String filePath = Utils.obj2Str(fileName);
      try {
        FileInputStream fis = new FileInputStream(filePath);
        thisObj.setExtra(fis);
      } catch (FileNotFoundException e) {
        throw new UnsupportedOperationException();
      }
    });

    Heap.registerMethod("java/io/FileInputStream_<init>_(Ljava/io/FileDescriptor;)V", frame -> {
      Instance fd = (Instance) frame.popRef();
      Instance thisObj = (Instance) frame.popRef();
      int fdInt = fd.getField("fd", "I").val.getInt();
      try {
        if (fdInt == 0) {
          FileInputStream fis = new FileInputStream(FileDescriptor.in);
          thisObj.setExtra(fis);
          return;
        }
        throw new UnsupportedOperationException();
      } catch (Exception e) {
        throw new UnsupportedOperationException();
      }
    });

    Heap.registerMethod("java/io/FileInputStream_available0_()I", frame -> {
      Instance thisObj = (Instance) frame.popRef();
      FileInputStream extra = (FileInputStream) thisObj.getExtra();
      try {
        int available = extra.available();
        frame.pushInt(available);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });

    Heap.registerMethod("java/io/FileInputStream_close0_()V", frame -> {
      Instance thisObj = (Instance) frame.popRef();
      FileInputStream extra = (FileInputStream) thisObj.getExtra();
      try {
        extra.close();
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });

    Heap.registerMethod("java/io/FileInputStream_read0_()I", frame -> {
      Instance thisObj = (Instance) frame.popRef();
      FileInputStream extra = (FileInputStream) thisObj.getExtra();
      try {
        int read = extra.read();
        frame.pushInt(read);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });

    Heap.registerMethod("java/io/FileInputStream_skip0_(J)J", frame -> {
      Long val = frame.popLong();
      Instance thisObj = (Instance) frame.popRef();
      FileInputStream extra = (FileInputStream) thisObj.getExtra();
      try {
        long skip = extra.skip(val);
        frame.pushLong(skip);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });

    Heap.registerMethod("java/io/FileInputStream_readBytes_([BII)I", frame -> {
      Integer v3 = frame.popInt();
      Integer v2 = frame.popInt();
      KArray v1 = (KArray) frame.popRef();
      byte[] ba = new byte[v1.items.length];
      Instance thisObj = (Instance) frame.popRef();
      FileInputStream extra = (FileInputStream) thisObj.getExtra();
      try {
        int read = extra.read(ba, v2, v3);

        for (int i = v2; i < v2 + v3; i++) {
          v1.items[i] = ba[i];
        }

        frame.pushInt(read);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });

    Heap.registerMethod("java/io/FileInputStream_initIDs_()V", frame -> {
    });
  }
}
