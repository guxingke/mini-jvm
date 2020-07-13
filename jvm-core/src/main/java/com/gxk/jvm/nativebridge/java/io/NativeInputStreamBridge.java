package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.MetaSpace;
import com.gxk.jvm.rtda.heap.KObject;

import java.io.IOException;
import java.io.InputStream;

public abstract class NativeInputStreamBridge {
  public static void registerNatives0() {
    MetaSpace.registerMethod("java/io/NativeInputStream_read_()I", frame -> {
      InputStream is = (InputStream) ((KObject) frame.popRef()).getExtra();
      try {
        int read = is.read();
        frame.pushInt(read);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });
    MetaSpace.registerMethod("java/io/NativeInputStream_available_()I", frame -> {
      InputStream is = (InputStream) ((KObject) frame.popRef()).getExtra();
      try {
        int available = is.available();
        frame.pushInt(available);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });
    MetaSpace.registerMethod("java/io/NativeInputStream_close_()V", frame -> {
      InputStream is = (InputStream) ((KObject) frame.popRef()).getExtra();
      try {
        is.close();
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });
  }
}
