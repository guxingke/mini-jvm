package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.heap.Heap;

import java.io.IOException;
import java.io.InputStream;

public abstract class NativeInputStreamBridge {
  public static void registerNatives0() {
    Heap.registerMethod("java/io/NativeInputStream_read_()I", frame -> {
      InputStream is = (InputStream) (frame.popRef()).getExtra();
      try {
        int read = is.read();
        frame.pushInt(read);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });
    Heap.registerMethod("java/io/NativeInputStream_available_()I", frame -> {
      InputStream is = (InputStream) (frame.popRef()).getExtra();
      try {
        int available = is.available();
        frame.pushInt(available);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });
    Heap.registerMethod("java/io/NativeInputStream_close_()V", frame -> {
      InputStream is = (InputStream) (frame.popRef()).getExtra();
      try {
        is.close();
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });
  }
}
