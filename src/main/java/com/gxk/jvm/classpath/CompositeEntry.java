package com.gxk.jvm.classpath;

import com.gxk.jvm.classfile.ClassFile;
import java.util.List;
import lombok.Data;

@Data
public class CompositeEntry implements Entry {

  private final List<Entry> entries;

  public CompositeEntry(List<Entry> entries) {
    this.entries = entries;
  }

  @Override
  public ClassFile findClass(String name) {
    return null;
  }
}
