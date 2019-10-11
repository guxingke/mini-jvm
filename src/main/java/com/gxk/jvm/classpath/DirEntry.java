package com.gxk.jvm.classpath;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.ClassReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import lombok.Data;

@Data
public class DirEntry implements Entry {

  public final Path dirPath;

  @Override
  public ClassFile findClass(String clazzName) {
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
}
