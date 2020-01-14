package com.gxk.jvm;

import com.gxk.jvm.classloader.ClassLoader;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.interpret.Interpreter;
import com.gxk.jvm.nativebridge.java.io.FileDescriptorBridge;
import com.gxk.jvm.nativebridge.java.io.FileOutputStreamBridge;
import com.gxk.jvm.nativebridge.java.io.PrintStreamBridge;
import com.gxk.jvm.nativebridge.java.lang.ClassBridge;
import com.gxk.jvm.nativebridge.java.lang.ClassLoaderBridge;
import com.gxk.jvm.nativebridge.java.lang.DoubleBridge;
import com.gxk.jvm.nativebridge.java.lang.ExceptionBridge;
import com.gxk.jvm.nativebridge.java.lang.FloatBridge;
import com.gxk.jvm.nativebridge.java.lang.IntegerBridge;
import com.gxk.jvm.nativebridge.java.lang.MathBridge;
import com.gxk.jvm.nativebridge.java.lang.ObjectBridge;
import com.gxk.jvm.nativebridge.java.lang.StringBridge;
import com.gxk.jvm.nativebridge.java.lang.SystemBridge;
import com.gxk.jvm.nativebridge.java.lang.ThrowableBridge;
import com.gxk.jvm.nativebridge.java.security.AccessControllerBridge;
import com.gxk.jvm.nativebridge.java.sum.misc.ReflectionBridge;
import com.gxk.jvm.nativebridge.java.util.RandomBridge;
import com.gxk.jvm.nativebridge.java.util.concurrent.AtomicLongBridge;
import com.gxk.jvm.nativebridge.sun.misc.UnsafeBridge;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.util.EnvHolder;

import java.nio.file.Path;
import java.nio.file.Paths;

public class VirtualMachine {

  public void run(Args cmd) {
    String home = java.lang.System.getenv("JAVA_HOME");
    if (home == null) {
      throw new IllegalStateException("must set env JAVA_HOME");
    }

    EnvHolder.init();
    if (cmd.verbose) {
      EnvHolder.verbose = true;
    }
    if (cmd.verboseTrace) {
      EnvHolder.verboseTrace = true;
    }
    if (cmd.verboseClass) {
      EnvHolder.verboseClass = true;
    }
    if (cmd.verboseDebug) {
      EnvHolder.verboseDebug = true;
    }

    Path jarPath = Paths.get(home, "jre", "lib");
    String classpath = cmd.classpath + EnvHolder.PATH_SEPARATOR + jarPath.toFile().getAbsolutePath() + EnvHolder.FILE_SEPARATOR + "*";
    Entry entry = Classpath.parse(classpath);

    ClassLoader classLoader = new ClassLoader("boot", entry);
    initVm(classLoader);

    String mainClass = cmd.clazz.replace(".", EnvHolder.FILE_SEPARATOR);
    classLoader.loadClass(mainClass);

    KClass clazz = Heap.findClass(mainClass);
    KMethod method = clazz.getMainMethod();
    if (method == null) {
      throw new IllegalStateException("not found main method");
    }

    new Interpreter().interpret(method, cmd.args);
  }

  public static void initVm(ClassLoader classLoader) {
    loadLibrary();
    loadFoundationClass(classLoader);
  }

  public static void loadLibrary() {
    ObjectBridge.registerNatives0();
    ClassBridge.registerNatives0();
    SystemBridge.registerNatives0();
    PrintStreamBridge.registerNatives0();
    FileOutputStreamBridge.registerNatives0();
    FileDescriptorBridge.registerNative0();
    MathBridge.registerNatives0();
    UnsafeBridge.registerNatives0();
    IntegerBridge.registerNatives0();
    FloatBridge.registerNatives0();
    DoubleBridge.registerNatives0();
    StringBridge.registerNatives0();
    RandomBridge.registerNatives0();
    ExceptionBridge.registerNatives0();
    ThrowableBridge.registerNatives0();
    AtomicLongBridge.registerNatives0();
    ReflectionBridge.registerNatives0();
    ClassLoaderBridge.registerNatives0();
    AccessControllerBridge.registerNative0();
  }

  private static void loadFoundationClass(ClassLoader classLoader) {
    // class
    KClass metaClass = classLoader.loadClass("java/lang/Class");
    for (KClass cls : Heap.getClasses()) {
      if (cls.getRuntimeClass() == null) {
        KObject obj = metaClass.newObject();
        cls.setRuntimeClass(obj);
        obj.setMetaClass(cls);
      }
    }

    // primitive
    classLoader.loadPrimitiveClass("char");
    classLoader.loadPrimitiveClass("boolean");
    classLoader.loadPrimitiveClass("byte");
    classLoader.loadPrimitiveClass("short");
    classLoader.loadPrimitiveClass("int");
    classLoader.loadPrimitiveClass("long");
    classLoader.loadPrimitiveClass("float");
    classLoader.loadPrimitiveClass("double");

    // string
    classLoader.loadClass("java/lang/String");

    // primitive wrapper
    classLoader.loadClass("java/lang/Character");
    classLoader.loadClass("java/lang/Boolean");
    classLoader.loadClass("java/lang/Byte");
    classLoader.loadClass("java/lang/Short");
    classLoader.loadClass("java/lang/Integer");
    classLoader.loadClass("java/lang/Long");
    classLoader.loadClass("java/lang/Float");
    classLoader.loadClass("java/lang/Double");
  }
}
