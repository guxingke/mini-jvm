package com.gxk.jvm.tools;

import com.gxk.jvm.ext.bc.ByteCodeInterpreter;

/**
 * generate simple asm code.
 *
 * java -cp target/mini-jvm.jar com.gxk.jvm.tools.AsmInt [asm file] [args]
 * e.g
 * java -cp target/mini-jvm.jar com.gxk.jvm.tools.AsmInt ext/Sum.bc 10
 */
public class AsmInt {

  public static void main(String[] args) {
    String file = args[0];

    if (args.length == 1) {
      ByteCodeInterpreter.interpreter(file);
      return;
    }

    int size = args.length - 1;
    String[] args2 = new String[size];
    System.arraycopy(args, 1, args2, 0, args2.length);

    Integer[] pargs = new Integer[size];
    for (int i = 0; i < args2.length; i++) {
      pargs[i] = Integer.parseInt(args2[i]);
    }

    ByteCodeInterpreter.interpreter(file, pargs);
  }
}
