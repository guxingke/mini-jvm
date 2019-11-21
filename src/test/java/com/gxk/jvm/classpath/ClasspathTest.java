package com.gxk.jvm.classpath;

import static org.junit.Assert.*;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.util.EnvHolder;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ClasspathTest {

  @Test
  public void parse() {
    String home = System.getenv("JAVA_HOME");
    Path jarPath = Paths.get(home, "jre", "lib", "rt.jar");

    Entry entry = Classpath.parse("example" + EnvHolder.PATH_SEPARATOR + jarPath.toFile().getAbsolutePath());

    ClassFile cf = entry.findClass("java/lang/Object".replace("/", EnvHolder.FILE_SEPARATOR));
    assertNotNull(cf);
  }

  @Test
  public void parse_2() {
    String home = System.getenv("JAVA_HOME");
    Path jarPath = Paths.get(home, "jre", "lib");

    Entry entry = Classpath.parse(
        "example" + EnvHolder.PATH_SEPARATOR + jarPath.toFile().getAbsolutePath() + EnvHolder.FILE_SEPARATOR + "*");

    ClassFile cf = entry.findClass("java/lang/Object".replace("/", EnvHolder.FILE_SEPARATOR));
    assertNotNull(cf);
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
    Entry entry = Classpath.doParseJar("example.jar");
    ClassFile cf = entry.findClass("Hello");
    assertNotNull(cf);
  }

  @Test
  public void doParseJar_package() {
    Entry entry = Classpath.doParseJar("example.jar");
    ClassFile cf = entry.findClass("example/Hello".replace("/", EnvHolder.FILE_SEPARATOR));
    assertNotNull(cf);
  }

  @Test
  public void doParseJar_jre_object() {
    String home = System.getenv("JAVA_HOME");
    Path jarPath = Paths.get(home, "jre", "lib", "rt.jar");
    Entry entry = Classpath.doParseJar(jarPath.toFile().getAbsolutePath());
    ClassFile cf = entry.findClass("java/lang/Object".replace("/", EnvHolder.FILE_SEPARATOR));
    assertNotNull(cf);
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
    boolean ret = Classpath.isWildcard("." + EnvHolder.FILE_SEPARATOR + "*");
    assertTrue(ret);
  }

  @Test
  public void isWildcard_bad_case() {
    boolean ret = Classpath.isWildcard("." + EnvHolder.FILE_SEPARATOR + "**");
    assertTrue(!ret);
  }

  @Test
  public void isJar() {
    boolean ret = Classpath.isJar("test.jar");
    assertTrue(ret);
  }
}