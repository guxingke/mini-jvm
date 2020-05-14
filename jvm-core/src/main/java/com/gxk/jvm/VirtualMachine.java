package com.gxk.jvm;

import com.gxk.jvm.classloader.ClassLoader;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.interpret.Interpreter;
import com.gxk.jvm.nativebridge.java.io.FileDescriptorBridge;
import com.gxk.jvm.nativebridge.java.io.FileInputStreamBridge;
import com.gxk.jvm.nativebridge.java.io.FileOutputStreamBridge;
import com.gxk.jvm.nativebridge.java.io.NativeInputStreamBridge;
import com.gxk.jvm.nativebridge.java.io.UnixFileSystemBridge;
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
import com.gxk.jvm.nativebridge.java.nio.charset.CharsetBridge;
import com.gxk.jvm.nativebridge.java.security.AccessControllerBridge;
import com.gxk.jvm.nativebridge.java.sum.misc.ReflectionBridge;
import com.gxk.jvm.nativebridge.java.util.PropertiesBridge;
import com.gxk.jvm.nativebridge.java.util.RandomBridge;
import com.gxk.jvm.nativebridge.java.util.ZipFileBridge;
import com.gxk.jvm.nativebridge.java.util.concurrent.AtomicLongBridge;
import com.gxk.jvm.nativebridge.sun.misc.UnsafeBridge;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.Thread;
import com.gxk.jvm.rtda.memory.Heap;
import com.gxk.jvm.rtda.memory.MethodArea;
import com.gxk.jvm.rtda.memory.KClass;
import com.gxk.jvm.rtda.memory.KField;
import com.gxk.jvm.rtda.memory.KMethod;
import com.gxk.jvm.util.EnvHolder;
import com.gxk.jvm.util.Utils;

public class VirtualMachine {

  public void run(Args cmd) {
    if (cmd.verbose) {
      EnvHolder.verbose = true;
    }
    if (cmd.verboseTrace) {
      EnvHolder.verboseTrace = true;
    }

    if (cmd.verboseCall) {
      EnvHolder.verboseCall = true;
    }
    if (cmd.verboseClass) {
      EnvHolder.verboseClass = true;
    }
    if (cmd.verboseDebug) {
      EnvHolder.verboseDebug = true;
    }

    Entry entry = Classpath.parse(Utils.classpath(cmd.classpath));
    ClassLoader classLoader = new ClassLoader("boot", entry);
    initVm(classLoader);
    String mainClass = Utils.replace(cmd.clazz, '.', EnvHolder.FILE_SEPARATOR.toCharArray()[0]);
    classLoader.loadClass(mainClass);

    KClass clazz = MethodArea.findClass(mainClass);
    KMethod method = clazz.getMainMethod();
    if (method == null) {
      throw new IllegalStateException("not found main method");
    }

    new Interpreter().interpret(method, cmd.args);
  }

  public static void initVm(ClassLoader classLoader) {
    loadLibrary();
    loadFoundationClass(classLoader);

    initSystemOut(classLoader);
    initSystemErr(classLoader);
  }

  private static void initSystemErr(ClassLoader classLoader) {
    KClass fdCls = classLoader.loadClass("java/io/FileDescriptor");
    Long outFdObj = fdCls.newObject();
    KMethod fdInitMethod = fdCls.getMethod("<init>", "(I)V");
    Thread t1 = new Thread(1024);
    Frame f1 = new Frame(fdInitMethod, t1);
    f1.setRef(0, outFdObj);
    f1.setInt(1, 2);
    new Interpreter().doInterpret(t1, f1);

    KClass fosCls = classLoader.loadClass("java/io/FileOutputStream");
    Long fosObj = fosCls.newObject();
    KMethod fosInitMethod = fosCls.getMethod("<init>", "(Ljava/io/FileDescriptor;)V");
    Thread t2 = new Thread(1024);
    Frame f2 = new Frame(fosInitMethod, t2);
    f2.setRef(0, fosObj);
    f2.setRef(1, outFdObj);
    new Interpreter().doInterpret(t2, f2);

    KClass psCls = classLoader.loadClass("java/io/PrintStream");
    Long psObj = psCls.newObject();
    KMethod psInitMethod = psCls.getMethod("<init>", "(Ljava/io/OutputStream;Z)V");
    Thread thread = new Thread(1024);
    Frame frame = new Frame(psInitMethod, thread);
    frame.setRef(0, psObj);
    frame.setRef(1, fosObj);
    frame.setInt(2, 1);
    new Interpreter().doInterpret(thread, frame);

    KClass sysCls = classLoader.loadClass("java/lang/System");
    KField outField = sysCls.getField("err", "Ljava/io/PrintStream;");
    outField.val = new Slot[]{new Slot(psObj)};

  }

  private static void initSystemOut(ClassLoader classLoader) {
    KClass fdCls = classLoader.loadClass("java/io/FileDescriptor");
    Long outFdObj = fdCls.newObject();
    KMethod fdInitMethod = fdCls.getMethod("<init>", "(I)V");
    Thread t1 = new Thread(1024);
    Frame f1 = new Frame(fdInitMethod, t1);
    f1.setRef(0, outFdObj);
    f1.setInt(1, 1);
    new Interpreter().doInterpret(t1, f1);

    KClass fosCls = classLoader.loadClass("java/io/FileOutputStream");
    Long fosObj = fosCls.newObject();
    KMethod fosInitMethod = fosCls.getMethod("<init>", "(Ljava/io/FileDescriptor;)V");
    Thread t2 = new Thread(1024);
    Frame f2 = new Frame(fosInitMethod, t2);
    f2.setRef(0, fosObj);
    f2.setRef(1, outFdObj);
    new Interpreter().doInterpret(t2, f2);

    KClass psCls = classLoader.loadClass("java/io/PrintStream");
    Long psObj = psCls.newObject();
    KMethod psInitMethod = psCls.getMethod("<init>", "(Ljava/io/OutputStream;Z)V");
    Thread thread = new Thread(1024);
    Frame frame = new Frame(psInitMethod, thread);
    frame.setRef(0, psObj);
    frame.setRef(1, fosObj);
    frame.setInt(2, 1);
    new Interpreter().doInterpret(thread, frame);

    KClass sysCls = classLoader.loadClass("java/lang/System");
    KField outField = sysCls.getField("out", "Ljava/io/PrintStream;");
    outField.val = new Slot[]{new Slot(psObj)};
  }

  public static void loadLibrary() {
    ObjectBridge.registerNatives0();
    ClassBridge.registerNatives0();
    SystemBridge.registerNatives0();
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
    PropertiesBridge.registerNative0();
    CharsetBridge.registerNative0();
    UnixFileSystemBridge.registerNatives0();
    FileInputStreamBridge.registerNatives0();
    ZipFileBridge.registerNatives0();
    NativeInputStreamBridge.registerNatives0();
  }

  private static void loadFoundationClass(ClassLoader classLoader) {
    // class
    KClass metaClass = classLoader.loadClass("java/lang/Class");
    for (KClass cls : MethodArea.getClasses()) {
      if (cls.getRuntimeClass() == null) {
        Long obj = metaClass.newObject();
        cls.setRuntimeClass(obj);
        Heap.load(obj).setMetaClass(cls);
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
    classLoader.loadPrimitiveClass("void");

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
//    classLoader.loadClass("java/lang/Double");
    classLoader.loadClass("java/lang/Void");

    // primitvie Arry class
    classLoader.loadPrimitiveArrayClass("[B");
    classLoader.loadPrimitiveArrayClass("[C");
    classLoader.loadPrimitiveArrayClass("[Z");
    classLoader.loadPrimitiveArrayClass("[S");
    classLoader.loadPrimitiveArrayClass("[I");
    classLoader.loadPrimitiveArrayClass("[F");
    classLoader.loadPrimitiveArrayClass("[L");
    classLoader.loadPrimitiveArrayClass("[D");
  }
}
