package com.gxk.jvm.classfile;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;

public class ClassReaderTest {

  @Test
  public void test() throws IOException {
    Path path = Paths.get("example/Hello.class");
    ClassFile cf = ClassReader.read(path);

    assertNotNull(cf);

    System.out.println(cf);
  }

}