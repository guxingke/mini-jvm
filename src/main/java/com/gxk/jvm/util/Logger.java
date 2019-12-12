package com.gxk.jvm.util;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.slf4j.helpers.MessageFormatter;

/**
 * inner logger util
 */
public abstract class Logger {

  public static void trace(String tpl, Object... args) {
    log("!T ", tpl, args);
  }

  public static void debug(String tpl, Object... args) {
    log("!D ", tpl, args);
  }

  public static void info(String tpl, Object... args) {
    log("!I ", tpl, args);
  }

  public static void error(String tpl, Object... args) {
    log("!E ", tpl, args);
  }

  private static void log(String prefix, String tpl, Object... args) {
    String msg = format(tpl, args);
    String[] lines = msg.split("\n");
    String out = Arrays.stream(lines).map(it -> prefix + it).collect(Collectors.joining("\n"));
    System.out.println(out);
  }

  private static String format(String tpl, Object... args) {
    return MessageFormatter.arrayFormat(tpl, args).getMessage();
  }
}
