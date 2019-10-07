package com.gxk.jvm;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {

    if (args.length == 0) {
      System.out.println("usage: java [options] class [args]\n");
      return;
    }

    Args cmd = Args.parseArgs(args);

    if (cmd.version) {
      System.out.println("java version \"1.8.0\"");
      return;
    }

    System.out.println(cmd.classpath);
    System.out.println(cmd.clazz);
    System.out.println(Arrays.toString(cmd.args));
  }
}
