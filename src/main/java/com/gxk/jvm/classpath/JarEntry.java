package com.gxk.jvm.classpath;

import com.gxk.jvm.classfile.ClassFile;
import java.nio.file.Path;
import lombok.Data;

@Data
public class JarEntry implements Entry {

  public final Path path;

  @Override
  public ClassFile findClass(String name) {
    return null;
  }
}
