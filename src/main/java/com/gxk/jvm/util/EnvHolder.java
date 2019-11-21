package com.gxk.jvm.util;

import java.util.Objects;

public abstract class EnvHolder {
  public static String PATH_SEPARATOR;
  public static String FILE_SEPARATOR;

  static {
    init();
  }

  public static void init() {
    PATH_SEPARATOR = isWindows() ? ";" : ":";
    FILE_SEPARATOR = isWindows() ? "\\" : "/";
  }

  public static boolean isWindows() {
    String osName = System.getProperty("os.name").toUpperCase();
    return Objects.equals("WINDOWS", osName);
  }
}
