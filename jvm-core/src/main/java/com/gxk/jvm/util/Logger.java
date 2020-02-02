package com.gxk.jvm.util;

import java.util.Arrays;
import org.slf4j.helpers.MessageFormatter;

/**
 * inner logger util
 */
public abstract class Logger {

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
    System.out.println(msg);
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
}
