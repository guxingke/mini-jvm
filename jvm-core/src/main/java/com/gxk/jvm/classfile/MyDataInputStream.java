package com.gxk.jvm.classfile;

import java.io.DataInputStream;
import java.io.IOException;

public class MyDataInputStream extends DataInputStream {

  public MyDataInputStream(MyByteArrayInputStream in) {
    super(in);
  }

  public int readPadding() {
    int offset = 0;
    while (((MyByteArrayInputStream) in).getPosition() % 4 != 0) {
      try {
        in.read();
        offset++;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return offset;
  }
}
