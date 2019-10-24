package com.gxk.jvm;

import com.gxk.jvm.classloader.ClassLoader;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.interpret.Interpreter;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KField;
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
    new Interpreter().initVm(vmClinit);

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
    Heap.registerMethod("java/lang/Object_hashCode_()I",
        (frame) -> frame.operandStack.pushInt(frame.operandStack.popRef().hashCode()));
    Heap.registerMethod("java/lang/Object_registerNatives_()V", (frame) -> {});
    Heap.registerMethod("java/lang/Object_clone_()Ljava/lang/Object;",
        (frame) -> frame.operandStack.pushRef(frame.operandStack.popRef()));
    Heap.registerMethod("java/lang/Object_getClass_()Ljava/lang/Class;",
        (frame) -> frame.operandStack.pushRef(frame.operandStack.popRef().getClass()));
    Heap.registerMethod("java/lang/Object_wait_(J)V", (frame) -> {});
    Heap.registerMethod("java/lang/Object_notify_()V", (frame) -> {});
    Heap.registerMethod("java/lang/Object_notifyAll_()V", (frame) -> {});
    // system
    Heap.registerMethod("java/lang/System_registerNatives_()V", (frame) -> {});
    Heap.registerMethod("java/lang/System_setIn0_(Ljava/io/InputStream;)V", (frame) -> {});
    Heap.registerMethod("java/lang/System_setOut0_(Ljava/io/PrintStream;)V", (frame) -> {});
    Heap.registerMethod("java/lang/System_setErr0_(Ljava/io/PrintStream;)V", (frame) -> {});
    Heap.registerMethod("java/lang/System_currentTimeMillis_()J",
        (frame) -> frame.operandStack.pushLong(System.currentTimeMillis()));
    Heap.registerMethod("java/lang/System_nanoTime_()J", (frame) -> frame.operandStack.pushLong(System.nanoTime()));
    Heap.registerMethod("java/lang/System_arraycopy_(Ljava/lang/Object;ILjava/lang/Object;II)V", (frame) -> {});
    Heap.registerMethod("java/lang/System_identityHashCode_(Ljava/lang/Object;)I",
        (frame) -> frame.operandStack.pushInt(frame.operandStack.popRef().hashCode()));
    Heap.registerMethod("java/lang/System_initProperties_(Ljava/util/Properties;)Ljava/util/Properties;", (frame) -> {});
    Heap.registerMethod("java/lang/System_mapLibraryName_(Ljava/lang/String;)Ljava/lang/String;",
        (frame) -> frame.operandStack.pushRef(frame.operandStack.popRef()));
    // in out err
    Heap.registerMethod("java/io/PrintStream_println_(Ljava/lang/String;)V", frame -> {
      Object val = frame.operandStack.popRef();
      frame.operandStack.popRef();
      System.out.println(val);
    });
    Heap.registerMethod("java/io/PrintStream_println_()V", frame -> {
      frame.operandStack.popRef();
      System.out.println();
    });
    Heap.registerMethod("java/io/PrintStream_println_(I)V", frame -> {
      Object val = frame.operandStack.popRef();
      frame.operandStack.popRef();
      System.out.println(val);
    });
    Heap.registerMethod("java/io/PrintStream_println_(J)V", frame -> {
      Object val = frame.operandStack.popRef();
      frame.operandStack.popRef();
      System.out.println(val);
    });

    // vm
    Heap.registerMethod("sun/misc/VM_latestUserDefinedLoader0_()Ljava/lang/ClassLoader;", (frame) -> {});
    Heap.registerMethod("sun/misc/VM_initialize_()V", (frame) -> {

      KClass sysClazz = Heap.findClass("java/lang/System");
      if (sysClazz == null) {
        KClass vmClass = frame.method.clazz;
        ClassLoader classLoader = vmClass.getClassLoader();
        sysClazz = classLoader.loadClass("java/lang/System");
      }

      if (!sysClazz.isStaticInit()) {
        KMethod clinitMethod = sysClazz.getMethod("<clinit>", "()V");
        Frame newFrame = new Frame(clinitMethod, frame.thread);
        sysClazz.setStaticInit(1);
        KClass finalSysClazz = sysClazz;
        newFrame.setOnPop(() -> finalSysClazz.setStaticInit(2));
        frame.thread.pushFrame(newFrame);
      }

      KField out = sysClazz.getField("out", "Ljava/io/PrintStream;");
      KField in = sysClazz.getField("in", "Ljava/io/InputStream;");
      KField err= sysClazz.getField("err", "Ljava/io/PrintStream;");

      out.val = new Slot[]{new Slot(System.out)};
      in.val = new Slot[]{new Slot(System.in)};
      err.val = new Slot[]{new Slot(System.err)};
    });
  }
}
