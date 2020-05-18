package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.KArray;
import com.gxk.jvm.rtda.memory.KObject;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.util.Utils;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class FileInputStreamBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/io/FileInputStream_open0_(Ljava/lang/String;)V", frame -> {
      KObject fileName = Heap.load(frame.popRef());
      KObject thisObj = Heap.load(frame.popRef());

      String filePath = Utils.obj2Str(fileName);
      try {
        FileInputStream fis = new FileInputStream(filePath);
        thisObj.setExtra(fis);
      } catch (FileNotFoundException e) {
        throw new UnsupportedOperationException();
      }
    });

    MethodArea.registerMethod("java/io/FileInputStream_<init>_(Ljava/io/FileDescriptor;)V", frame -> {
      KObject fd = Heap.load(frame.popRef());
      KObject thisObj = Heap.load(frame.popRef());
      Integer fdInt = fd.getField("fd", "I").val()[0].num;
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

    MethodArea.registerMethod("java/io/FileInputStream_available0_()I", frame -> {
      KObject thisObj = Heap.load(frame.popRef());
      FileInputStream extra = (FileInputStream) thisObj.getExtra();
      try {
        int available = extra.available();
        frame.pushInt(available);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });

    MethodArea.registerMethod("java/io/FileInputStream_close0_()V", frame -> {
      KObject thisObj = Heap.load(frame.popRef());
      FileInputStream extra = (FileInputStream) thisObj.getExtra();
      try {
        extra.close();
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });

    MethodArea.registerMethod("java/io/FileInputStream_read0_()I", frame -> {
      KObject thisObj = Heap.load(frame.popRef());
      FileInputStream extra = (FileInputStream) thisObj.getExtra();
      try {
        int read = extra.read();
        frame.pushInt(read);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });

    MethodArea.registerMethod("java/io/FileInputStream_skip0_(J)J", frame -> {
      Long val = frame.popLong();
      KObject thisObj = Heap.load(frame.popRef());
      FileInputStream extra = (FileInputStream) thisObj.getExtra();
      try {
        long skip = extra.skip(val);
        frame.pushLong(skip);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });

    MethodArea.registerMethod("java/io/FileInputStream_readBytes_([BII)I", frame -> {
      Integer v3 = frame.popInt();
      Integer v2 = frame.popInt();
      KArray v1 = (KArray) Heap.load(frame.popRef());
      byte[] ba = new byte[v1.items.length];
      KObject thisObj = Heap.load(frame.popRef());
      FileInputStream extra = (FileInputStream) thisObj.getExtra();
      try {
        int read = extra.read(ba, v2, v3);

        for (int i = v2; i < v2 + v3; i++) {
          v1.primitiveItems[i] = ba[i];
        }

        frame.pushInt(read);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });

    MethodArea.registerMethod("java/io/FileInputStream_initIDs_()V", frame -> {
    });
  }
}
