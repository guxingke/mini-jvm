package com.gxk.jvm;

import com.gxk.jvm.classloader.ClassLoader;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.interpret.Interpreter;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VirtualMachine {

  public void run(Args cmd) {
    String home = System.getenv("JAVA_HOME");
    if (home == null) {
      throw new IllegalStateException("must set env JAVA_HOME");
    }

    Path jarPath = Paths.get(home, "jre", "lib");
    String classpath = cmd.classpath + ":" + jarPath.toFile().getAbsolutePath() + "/*";
    Entry entry = Classpath.parse(classpath);

    // load library
    loadLibrary();

    ClassLoader classLoader = new ClassLoader("boot", entry);

    // init vm
    KClass vmClass = classLoader.loadClass("sun/misc/VM");
    KMethod vmClinit = vmClass.getMethod("<clinit>", "()V");
    new Interpreter().interpret(vmClinit);

    // exec main
    String mainClass = cmd.clazz.replace(".", "/");
    classLoader.loadClass(mainClass);

    KClass clazz = Heap.findClass(mainClass);
    KMethod method = clazz.getMainMethod();
    if (method == null) {
      throw new IllegalStateException("not found main method");
    }

    new Interpreter().interpret(method, cmd.args);
  }

  public static void loadLibrary() {
    // object
    Heap.registerMethod("java/lang/Object_hashCode_()I", (args) -> args[0].hashCode());
    Heap.registerMethod("java/lang/Object_registerNatives_()V", (args) -> null);
    Heap.registerMethod("java/lang/Object_clone_()Ljava/lang/Object;", (args) -> args[0]);
    Heap.registerMethod("java/lang/Object_getClass_()Ljava/lang/Class;", (args) -> args[0].getClass());
    Heap.registerMethod("java/lang/Object_wait_(J)V", (args) -> null);
    Heap.registerMethod("java/lang/Object_notify_()V", (args) -> null);
    Heap.registerMethod("java/lang/Object_notifyAll_()V", (args) -> null);
    // system
    Heap.registerMethod("java/lang/System_registerNatives_()V", (args) -> null);
    Heap.registerMethod("java/lang/System_setIn0_(Ljava/io/InputStream;)V", (args) -> null);
    Heap.registerMethod("java/lang/System_setOut0_(Ljava/io/PrintStream;)V", (args) -> null);
    Heap.registerMethod("java/lang/System_setErr0_(Ljava/io/PrintStream;)V", (args) -> null);
    Heap.registerMethod("java/lang/System_currentTimeMillis_()J", (args) -> System.currentTimeMillis());
    Heap.registerMethod("java/lang/System_nanoTime_()J", (args) -> System.nanoTime());
    Heap.registerMethod("java/lang/System_arraycopy_(Ljava/lang/Object;ILjava/lang/Object;II)V", (args) -> null);
    Heap.registerMethod("java/lang/System_identityHashCode_(Ljava/lang/Object;)I", (args) -> args[0].hashCode());
    Heap.registerMethod("java/lang/System_initProperties_(Ljava/util/Properties;)Ljava/util/Properties;", (args) -> null);
    Heap.registerMethod("java/lang/System_mapLibraryName_(Ljava/lang/String;)Ljava/lang/String;", (args) -> args[0]);
  }
}
