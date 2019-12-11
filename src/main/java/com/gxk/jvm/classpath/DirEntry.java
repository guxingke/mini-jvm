package com.gxk.jvm.classpath;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.ClassReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class DirEntry implements Entry {

  public final Path dirPath;

  public DirEntry(Path dirPath) {
    this.dirPath = dirPath;
  }

  @Override
  public ClassFile findClass(String clazzName) {
    if (!clazzName.contains("/")) {
      return Arrays.stream(
        Objects.requireNonNull(dirPath.toFile().listFiles((dir, name) -> Objects.equals(name, clazzName + ".class"))))
        .findFirst()
        .map(it -> {
          try {
            return ClassReader.read(Paths.get(it.getAbsolutePath()));
          } catch (IOException e) {
            return null;
          }
        })
        .orElse(null);
    }

    int idx = clazzName.indexOf("/");
    String subDir = clazzName.substring(0, idx);
    Path subPath = dirPath.resolve(subDir);
    if (!subPath.toFile().exists()) {
      return null;
    }
    return new DirEntry(subPath).findClass(clazzName.substring(idx + 1));
  }
}
