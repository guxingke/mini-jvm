package com.gxk.jvm.classfile.attribute;

import com.gxk.jvm.classfile.Attribute;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BootstrapMethods extends Attribute {

  public final BootstrapMethod[] methods;

  @AllArgsConstructor
  @Data
  public static class BootstrapMethod {
    public final Integer bootstrapMethodRefInx;
    public final Integer[] argsRefs;
  }
}
