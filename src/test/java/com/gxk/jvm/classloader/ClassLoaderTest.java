package com.gxk.jvm.classloader;

import com.gxk.jvm.VirtualMachine;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
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
    Entry entry = Classpath.parse("example:" + jarPath.toFile().getAbsolutePath());
    classLoader = new ClassLoader("boot", entry);
    VirtualMachine.loadLibrary();
  }

  @After
  public void tearDown() {
    Heap.clear();
  }

  @Test
  public void test_object() {
    KClass kClass = classLoader.loadClass("java/lang/Object");
    assertNotNull(kClass);
  }

  @Test
  public void test_hello() {
    KClass kClass = classLoader.loadClass("Hello");
    assertNotNull(kClass);
  }

  @Test
  public void test_system() {
    KClass kClass = classLoader.loadClass("java/lang/System");
    assertNotNull(kClass);
  }
}