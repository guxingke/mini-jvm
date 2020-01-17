package com.gxk.jvm.tools;

import com.gxk.jvm.ext.bc.ByteCodeGenerator;

/**
 * generate simple asm code from class file method.
 *
 * java -cp target/tools.jar com.gxk.jvm.tools.AsmGen [class file] [method]
 * e.g
 * java -cp target/tools.jar com.gxk.jvm.tools.AsmGen ext/Sum.class sum
 */
public class AsmGen {

  public static void main(String[] args) {
    String clazzPath = args[0];
    String methodName = "bc";
    if (args.length == 2) {
      methodName = args[1];
    }

    ByteCodeGenerator.gen(clazzPath, methodName);
  }
}
