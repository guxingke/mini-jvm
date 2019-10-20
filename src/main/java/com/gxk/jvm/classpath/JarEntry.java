package com.gxk.jvm.classpath;

import com.gxk.jvm.classfile.ClassFile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.jar.JarFile;

import com.gxk.jvm.classfile.ClassReader;
import lombok.Data;

@Data
public class JarEntry implements Entry {

  public final Path path;

  @Override
  public ClassFile findClass(String name) {
    JarFile file;
    try {
      file = new JarFile(path.toFile());
    } catch (IOException e) {
      throw new IllegalStateException();
    }

    java.util.jar.JarEntry jarEntry = file.getJarEntry(name + ".class");

    try (InputStream is = file.getInputStream(jarEntry)) {
      ClassFile cf = ClassReader.read(new DataInputStream(new BufferedInputStream(is)));
      return cf;
    } catch (Exception e) {
      throw new IllegalStateException();
    }
  }
}
