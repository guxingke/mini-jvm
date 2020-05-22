package com.gxk.jvm.rtda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Threads {

  private static final Map<String, Thread> threads = new HashMap<>();

  public static void register(String name, Thread thread) {
    threads.put(name, thread);
  }

  public static void remove(String name) {
    threads.remove(name);
  }

  public static List<Thread> threads() {
    return new ArrayList<>(threads.values());
  }
}
