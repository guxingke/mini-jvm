package com.gxk.jvm.classpath;

import static org.junit.Assert.*;

import com.gxk.jvm.classfile.ClassFile;
import org.junit.Test;

public class ClasspathTest {

  @Test
  public void parse() {
  }

  @Test
  public void parse_dir() {
    Entry entry = Classpath.parse("example");
    ClassFile hello = entry.findClass("Hello");
    assertNotNull(hello);
  }

  @Test
  public void doParseCompositeEntry() {
  }

  @Test
  public void parseEntry() {
  }

  @Test
  public void doParseWildcard() {
  }

  @Test
  public void doParseJar() {
  }

  @Test
  public void doParseDir() {
    Entry entry = Classpath.doParseDir("example");
    ClassFile hello = entry.findClass("Hello");
    assertNotNull(hello);
  }

  @Test
  public void isDir() {
    String dir = "example";
    boolean ret = Classpath.isDir(dir);
    assertTrue(ret);
  }

  @Test
  public void isDir_bad_case() {
    String dir = "example1111";
    boolean ret = Classpath.isDir(dir);
    assertTrue(!ret);
  }

  @Test
  public void isWildcard() {
    boolean ret = Classpath.isWildcard("./*");
    assertTrue(ret);
  }

  @Test
  public void isWildcard_bad_case() {
    boolean ret = Classpath.isWildcard("./**");
    assertTrue(!ret);
  }

  @Test
  public void isJar() {
    boolean ret = Classpath.isJar("test.jar");
    assertTrue(ret);
  }
}