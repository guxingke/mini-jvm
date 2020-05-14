package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KObject;

import java.io.IOException;
import java.io.InputStream;

public abstract class NativeInputStreamBridge {

  public static void registerNatives0() {
    MethodArea.registerMethod("java/io/NativeInputStream_read_()I", frame -> {
      InputStream is = (InputStream) ((KObject) Heap.load(frame.popRef())).getExtra();
      try {
        int read = is.read();
        frame.pushInt(read);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });
    MethodArea.registerMethod("java/io/NativeInputStream_available_()I", frame -> {
      InputStream is = (InputStream) (Heap.load(frame.popRef())).getExtra();
      try {
        int available = is.available();
        frame.pushInt(available);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });
    MethodArea.registerMethod("java/io/NativeInputStream_close_()V", frame -> {
      InputStream is = (InputStream) (Heap.load(frame.popRef())).getExtra();
      try {
        is.close();
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });
  }
}
