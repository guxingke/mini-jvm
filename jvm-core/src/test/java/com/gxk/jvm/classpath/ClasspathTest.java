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

    Entry entry = Classpath.parse(jarPath.toFile().getAbsolutePath());

    ClassFile cf = entry.findClass("java/lang/Object");
    assertNotNull(cf);
  }

  @Test
  public void parse_2() {
    String home = System.getenv("JAVA_HOME");

    Path jarPath = Paths.get(home, "jre", "lib");

    Entry entry = Classpath.parse(exampleJarPath().toFile().getAbsolutePath() + EnvHolder.PATH_SEPARATOR + jarPath.toFile().getAbsolutePath() + EnvHolder.FILE_SEPARATOR +"*");

    ClassFile cf = entry.findClass("java/lang/Object");

    assertNotNull(cf);
  }

  @Test
  public void parse_dir() {
    Entry entry = Classpath.parse(exampleJarPath().toFile().getAbsolutePath());
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
    Entry entry = Classpath.doParseJar(exampleJarPath().toFile().getAbsolutePath());
    ClassFile cf = entry.findClass("Hello");
    assertNotNull(cf);
  }

  @Test
  public void doParseJar_jre_object() {
    String home = System.getenv("JAVA_HOME");
    Path jarPath = Paths.get(home, "jre", "lib", "rt.jar");
    Entry entry = Classpath.doParseJar(jarPath.toFile().getAbsolutePath());
    ClassFile cf = entry.findClass("java/lang/Object");
    assertNotNull(cf);
  }

  @Test
  public void doParseDir() {
  }

  @Test
  public void isDir() {
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

  private Path exampleJarPath() {
    // check MINI_JVM_HOME ready
    // 1. env
    String miniJvmHome = System.getenv("MINI_JVM_HOME");
    if (miniJvmHome == null) {
      // 1.2 check current dir
      String userDir = System.getProperty("user.dir");
      if (userDir.endsWith("jvm-core")) {
        int idx = userDir.lastIndexOf(EnvHolder.FILE_SEPARATOR);
        miniJvmHome = userDir.substring(0, idx);
      } else if (userDir.endsWith("mini-jvm")) {
        miniJvmHome = userDir;
      }
    }
    if (miniJvmHome == null) {
      throw new IllegalStateException("MINI_JVM_HOME not found");
    }

    Path exampleJarPath= Paths.get(miniJvmHome, "example", "target", "example.jar");
    if (!exampleJarPath.toFile().exists()) {
      throw new IllegalStateException("example.jar not found");
    }

    return exampleJarPath;
  }
}