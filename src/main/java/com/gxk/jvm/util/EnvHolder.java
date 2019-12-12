package com.gxk.jvm.util;

public abstract class EnvHolder {
  public static String PATH_SEPARATOR;
  public static String FILE_SEPARATOR;

  public static boolean verbose;
  public static boolean trace;

  static {
    init();
  }

  public static void init() {
    PATH_SEPARATOR = isWindows() ? ";" : ":";
    FILE_SEPARATOR = isWindows() ? "\\" : "/";
  }

  public static boolean isWindows() {
    String osName = System.getProperty("os.name").toUpperCase();
    return osName.startsWith("WINDOWS");
  }
}
