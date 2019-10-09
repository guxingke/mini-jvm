package com.gxk.jvm.classfile.cp;

import com.gxk.jvm.classfile.ConstantInfo;
import com.gxk.jvm.classfile.ConstantPoolInfoEnum;
import java.nio.charset.StandardCharsets;
import lombok.Data;

@Data
public class Utf8 extends ConstantInfo {

  public final byte[] bytes;

  public Utf8(ConstantPoolInfoEnum infoEnum, byte[] bytes) {
    super(infoEnum);
    this.bytes = bytes;
  }

  public final String getString() {
    return new java.lang.String(bytes, StandardCharsets.UTF_8);
  }
}
