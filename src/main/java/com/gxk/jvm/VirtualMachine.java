package com.gxk.jvm;

import com.gxk.jvm.classloader.ClassLoader;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.interpret.Interpreter;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KField;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
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

    ClassLoader classLoader = new ClassLoader("boot", entry);
    initVm(classLoader);

    String mainClass = cmd.clazz.replace(".", "/");
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

    classLoader.loadClass("java/lang/String");
  }

  public static void loadLibrary() {
    // hack for out.println
    Heap.registerMethod("java/io/PrintStream_println_(Ljava/lang/String;)V", frame -> {
      Object val = frame.popRef();
      frame.popRef();
      if (val instanceof String) {
        System.out.println(val);
        return;
      }

      KField value = ((KObject) val).getField("value", "[C");
      KArray arr = (KArray) value.val[0].ref;
      char[] chars = new char[arr.items.length];
      for (int i = 0; i < arr.items.length; i++) {
        chars[i] = ((Character) arr.items[i]);
      }
      System.out.println(new String(chars));
    });
    Heap.registerMethod("java/io/PrintStream_println_()V", frame -> {
      frame.popRef();
      System.out.println();
    });
    Heap.registerMethod("java/io/PrintStream_println_(I)V", frame -> {
      Object val = frame.popInt();
      frame.popRef();
      System.out.println(val);
    });
    Heap.registerMethod("java/io/PrintStream_println_(Z)V", frame -> {
      int val = frame.popInt();
      frame.popRef();
      System.out.println(val == 1);
    });

    // object
    Heap.registerMethod("java/lang/Object_hashCode_()I", (frame) -> frame.pushInt(frame.popRef().hashCode()));
    Heap.registerMethod("java/lang/Object_registerNatives_()V", (frame) -> {});
    Heap.registerMethod("java/lang/Object_clone_()Ljava/lang/Object;", (frame) -> frame.pushRef(frame.popRef()));
    Heap.registerMethod("java/lang/Object_getClass_()Ljava/lang/Class;", (frame) -> frame.pushRef(frame.popRef().getClass()));
    Heap.registerMethod("java/lang/Object_wait_(J)V", (frame) -> {});
    Heap.registerMethod("java/lang/Object_notify_()V", (frame) -> {});
    Heap.registerMethod("java/lang/Object_notifyAll_()V", (frame) -> {});

    // class
    Heap.registerMethod("java/lang/Class_registerNatives_()V", (frame) -> {});
    Heap.registerMethod("java/lang/Class_getPrimitiveClass_(Ljava/lang/String;)Ljava/lang/Class;", (frame) -> {
      Character[] values = (Character[]) ((KArray) ((KObject) frame.popRef()).getField("value", "[C").val[0].ref).items;
      char[] v2 = new char[values.length];
      for (int i = 0; i < values.length; i++) {
        v2[i] = values[i];
      }
      String val = new String(v2);
      KClass kClass = new KClass("Ljava/lang/Class", frame.method.clazz.classLoader);
      KObject kObject = kClass.newObject();
      frame.pushRef(kObject);
    });
    // hack for class
    Heap.registerMethod("java/lang/Class_desiredAssertionStatus_()Z", frame -> {
      Object xx = frame.popRef();
      frame.pushInt(1);
    });

    // math
    // hack for math
    Heap.registerMethod("java/lang/Math_min_(II)I", frame -> {
      Integer v2 = frame.popInt();
      Integer v1 = frame.popInt();
      if (v1 <= v2) {
        frame.pushInt(v1);
      } else {
        frame.pushInt(v2);
      }
    });
    Heap.registerMethod("java/lang/Math_max_(II)I", frame -> {
      Integer v2 = frame.popInt();
      Integer v1 = frame.popInt();
      if (v1 >= v2) {
        frame.pushInt(v1);
      } else {
        frame.pushInt(v2);
      }
    });

    // system
    Heap.registerMethod("java/lang/System_registerNatives_()V", (frame) -> {});
    Heap.registerMethod("java/lang/System_setIn0_(Ljava/io/InputStream;)V", (frame) -> {});
    Heap.registerMethod("java/lang/System_setOut0_(Ljava/io/PrintStream;)V", (frame) -> {});
    Heap.registerMethod("java/lang/System_setErr0_(Ljava/io/PrintStream;)V", (frame) -> {});
    Heap.registerMethod("java/lang/System_currentTimeMillis_()J", (frame) -> frame.pushLong(System.currentTimeMillis()));
    Heap.registerMethod("java/lang/System_nanoTime_()J", (frame) -> frame.pushLong(System.nanoTime()));
    Heap.registerMethod("java/lang/System_arraycopy_(Ljava/lang/Object;ILjava/lang/Object;II)V", (frame) -> {
      Integer len= frame.popInt();
      Integer dsp = frame.popInt();
      KArray dest = (KArray) frame.popRef();
      Integer ssp = frame.popInt();
      KArray source = (KArray) frame.popRef();

      for (int i = 0; i < len; i++) {
        dest.items[dsp++] = source.items[ssp++];
      }
    });
    Heap.registerMethod("java/lang/System_identityHashCode_(Ljava/lang/Object;)I", (frame) -> frame.pushInt(frame.popRef().hashCode()));
    Heap.registerMethod("java/lang/System_initProperties_(Ljava/util/Properties;)Ljava/util/Properties;", (frame) -> {});
    Heap.registerMethod("java/lang/System_mapLibraryName_(Ljava/lang/String;)Ljava/lang/String;", (frame) -> {});
    // string
    Heap.registerMethod("java/lang/String_intern_()Ljava/lang/String;", frame ->  {
      System.out.println();
    });

    // Float
    Heap.registerMethod("java/lang/Float_intBitsToFloat_(I)F", frame -> {
      Integer tmp = frame.popInt();
      float v = Float.intBitsToFloat(tmp);
      frame.pushFloat(v);
    });
    Heap.registerMethod("java/lang/Float_floatToRawIntBits_(F)I", frame -> {
      float tmp = frame.popFloat();
      int v = Float.floatToRawIntBits(tmp);
      frame.pushInt(v);
    });
  }
}
