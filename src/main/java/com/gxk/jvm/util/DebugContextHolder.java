package com.gxk.jvm.util;

import java.util.Scanner;

public abstract class DebugContextHolder {

  public static Scanner scanner;
  public static boolean next;
  public static boolean running;
  public static String mainClass;
  public static boolean step;

  public static boolean isContinue() {
    if (step) {
      step = false;
      return true;
    }
    return next;
  }
}
