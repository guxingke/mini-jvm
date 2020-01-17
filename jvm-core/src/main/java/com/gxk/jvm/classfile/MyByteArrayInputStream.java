package com.gxk.jvm.classfile;

import java.io.ByteArrayInputStream;

public class MyByteArrayInputStream extends ByteArrayInputStream {
  public MyByteArrayInputStream(byte[] buf) {
    super(buf);
  }

  public int getPosition() {
    return this.pos;
  }
}
