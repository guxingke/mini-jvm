package com.gxk.jvm.util;

import com.gxk.jvm.classfile.ConstantPool;
import com.gxk.jvm.classfile.cp.Utf8;
import java.io.DataInputStream;
import java.io.IOException;

public abstract class Utils {

  public static byte[] readNBytes(DataInputStream stream, int n) throws IOException {
    byte[] bytes = new byte[n];
    for (int i = 0; i < n; i++) {
      bytes[i] = stream.readByte();
    }
    return bytes;
  }

  public static String getString(ConstantPool cp, int index) {
    return ((Utf8) cp.infos[index - 1]).getString();
  }
}
