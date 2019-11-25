package com.gxk.jvm.interpret;

import com.gxk.jvm.VirtualMachine;
import com.gxk.jvm.classloader.ClassLoader;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.util.EnvHolder;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.After;

public abstract class BaseInterpreterTest {

  @After
  public void tearDown() {
    Heap.clear();
  }

  protected void testMain(String hello) {
    KMethod method = loadAndGetMainMethod(hello);
    new Interpreter().interpret(method);
  }

  protected KMethod loadAndGetMainMethod(String clazzName) {
    KClass clazz = this.loadAndGetClazz(clazzName);
    KMethod method = clazz.getMainMethod();
    return method;
  }

  protected KClass loadAndGetClazz(String clazzName) {
    String home = System.getenv("JAVA_HOME");
    Path jarPath = Paths.get(home, "jre", "lib", "rt.jar");
    Entry entry = Classpath.parse("example" + EnvHolder.PATH_SEPARATOR + "onjava8" + EnvHolder.PATH_SEPARATOR + jarPath.toFile().getAbsolutePath());
    ClassLoader loader = new ClassLoader("boot", entry);
    VirtualMachine.initVm(loader);
    KClass clazz = loader.loadClass(clazzName);
    return clazz;
  }
}