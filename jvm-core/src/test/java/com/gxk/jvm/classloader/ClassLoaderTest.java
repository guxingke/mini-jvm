package com.gxk.jvm.classloader;

import com.gxk.jvm.VirtualMachine;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.Class;
import com.gxk.jvm.util.EnvHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ClassLoaderTest {

  private ClassLoader classLoader;

  @Before
  public void setup() {
    String home = System.getenv("JAVA_HOME");
    Path jarPath = Paths.get(home, "jre", "lib", "rt.jar");

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

    Entry entry = Classpath.parse(exampleJarPath.toFile().getAbsolutePath() + EnvHolder.PATH_SEPARATOR  + jarPath.toFile().getAbsolutePath());
    classLoader = new ClassLoader("boot", entry);
    VirtualMachine.loadLibrary();
  }

  @After
  public void tearDown() {
    Heap.clear();
  }

  @Test
  public void test_object() {
    Class aClass = classLoader.loadClass("java/lang/Object");
    assertNotNull(aClass);
  }

  @Test
  public void test_hello() {
    Class aClass = classLoader.loadClass("Hello");
    assertNotNull(aClass);
  }

  @Test
  public void test_onjava8_passobject() {
    Class clazz = classLoader.loadClass("PassObject");
    assertNotNull(clazz);
  }

  @Test
  public void test_system() {
    Class aClass = classLoader.loadClass("java/lang/System");
    assertNotNull(aClass);
  }
}