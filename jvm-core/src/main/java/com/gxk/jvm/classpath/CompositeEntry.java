package com.gxk.jvm.classpath;

import com.gxk.jvm.classfile.ClassFile;
import java.util.List;

public class CompositeEntry implements Entry {

  private final List<Entry> entries;

  public CompositeEntry(List<Entry> entries) {
    this.entries = entries;
  }

  @Override
  public ClassFile findClass(String name) {
    for (Entry entry : entries) {
      ClassFile cf = entry.findClass(name);
      if (cf != null) {
        return cf;
      }
    }
    return null;
  }
}
