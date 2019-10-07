package com.gxk.jvm.classfile;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ClassReaderTest {

  @Test
  public void test() throws IOException {
    Path path = Paths.get("example/Hello.class");
    InputStream is = Files.newInputStream(path);

    ClassReader cr = new ClassReader();

    ClassFile cf = cr.read(is);

    assertNotNull(cf);
  }

}