package com.gxk.jvm.util;

/**
 * inner logger util
 */
public abstract class Logger {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

  public static String fg = ANSI_RESET;

  public static void trace(String msg) {
    log("!T " + msg);
  }

  public static void clazz(String msg) {
    log("!C " + msg);
  }

  public static void debug(String tpl, Object... args) {
    log("!D ", tpl, args);
  }

  private static void log(String msg) {
    System.out.println(fg + msg + ANSI_RESET);
  }

  private static void log(String prefix, String tpl, Object... args) {
//    String msg = format(tpl, args);
//    String[] lines = msg.split("\n");
//
//    StringBuilder sb = new StringBuilder();
//    for (String line : lines) {
//      sb.append(prefix).append(line).append("\n");
//    }
//    System.out.println(sb.toString());
    System.out.println("xxxxxxx");
  }

  public static void error(String msg) {
    log("!E " + msg);
  }

  public static void red(String msg) {
    System.out.println(ANSI_RED + msg + ANSI_RESET);
  }
}
