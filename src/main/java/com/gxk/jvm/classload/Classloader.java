package com.gxk.jvm.classload;

import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;

public class Classloader {

  public void loadClass(String name, Entry entry, Heap heap) {
    KClass clazz = doLoadClass(name, entry);
    heap.registerClass(clazz.name, clazz);
  }

  private KClass doLoadClass(String name, Entry entry) {

    return null;
  }
}
