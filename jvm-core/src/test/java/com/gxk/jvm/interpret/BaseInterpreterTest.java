package com.gxk.jvm.interpret;

import com.gxk.jvm.VirtualMachine;
import com.gxk.jvm.classloader.ClassLoader;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KClass;
import com.gxk.jvm.rtda.memory.KMethod;
import com.gxk.jvm.util.EnvHolder;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gxk.jvm.util.Utils;
import org.junit.After;

public abstract class BaseInterpreterTest {

  @After
  public void tearDown() {
    MethodArea.clear();
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

    String classpath = Utils.classpath(exampleJarPath.toFile().getAbsolutePath());
    Entry entry = Classpath.parse(classpath);
    ClassLoader loader = new ClassLoader("boot", entry);
    VirtualMachine.initVm(loader);
    KClass clazz = loader.loadClass(clazzName);
    return clazz;
  }
}