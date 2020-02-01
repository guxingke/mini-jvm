package com.gxk.jvm.classpath;

import com.gxk.jvm.classfile.ClassFile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import com.gxk.jvm.classfile.ClassReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarEntry implements Entry {

  public final Path path;

  public JarEntry(Path path) {
    this.path = path;
  }

  @Override
  public ClassFile findClass(String name) {
    ZipFile file;
    try {
      file = new ZipFile(path.toFile());
    } catch (IOException e) {
      throw new IllegalStateException();
    }

    ZipEntry entry = file.getEntry(name + ".class");
    if (entry == null) {
      return null;
    }

    try (InputStream is = file.getInputStream(entry)) {
      ClassFile cf = ClassReader.read(new DataInputStream(new BufferedInputStream(is)));
      cf.setSource(path.toString());

      return cf;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException();
    }
  }
}
