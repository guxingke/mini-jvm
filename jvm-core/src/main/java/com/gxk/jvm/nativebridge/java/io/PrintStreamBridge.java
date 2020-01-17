package com.gxk.jvm.nativebridge.java.io;

import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KField;
import com.gxk.jvm.rtda.heap.KObject;

public abstract class PrintStreamBridge {

  public static void registerNatives0() {
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
    Heap.registerMethod("java/io/PrintStream_println_(Z)V", frame -> {
      int val = frame.popInt();
      frame.popRef();
      System.out.println(val == 1);
    });
    Heap.registerMethod("java/io/PrintStream_println_(C)V", frame -> {
      int val = frame.popInt();
      frame.popRef();
      System.out.println(((char) val));
    });
    Heap.registerMethod("java/io/PrintStream_println_(B)V", frame -> {
      int val = frame.popInt();
      frame.popRef();
      System.out.println(((byte) val));
    });
    Heap.registerMethod("java/io/PrintStream_println_(S)V", frame -> {
      int val = frame.popInt();
      frame.popRef();
      System.out.println(((short) val));
    });
    Heap.registerMethod("java/io/PrintStream_println_(I)V", frame -> {
      Object val = frame.popInt();
      frame.popRef();
      System.out.println(val);
    });
    Heap.registerMethod("java/io/PrintStream_println_(F)V", frame -> {
      float val = frame.popFloat();
      frame.popRef();
      System.out.println((val));
    });
    Heap.registerMethod("java/io/PrintStream_println_(J)V", frame -> {
      long val = frame.popLong();
      frame.popRef();
      System.out.println(val);
    });
    Heap.registerMethod("java/io/PrintStream_println_(D)V", frame -> {
      double val = frame.popDouble();
      frame.popRef();
      System.out.println(val);
    });
    Heap.registerMethod("java/io/PrintStream_println_(Ljava/lang/Object;)V", frame -> {
      Object val = frame.popRef();
      if (val instanceof KObject) {
        KObject valObj = (KObject) val;
        if (valObj.clazz.name.equals("java/lang/Integer")) {
          val = valObj.getField("value", "I").val[0].num;
        }
      }
      frame.popRef();
      System.out.println(val);
    });

    Heap.registerMethod("java/io/PrintStream_print_(Ljava/lang/String;)V", frame -> {
      Object val = frame.popRef();
      frame.popRef();
      if (val instanceof String) {
        System.out.print(val);
        return;
      }

      KField value = ((KObject) val).getField("value", "[C");
      KArray arr = (KArray) value.val[0].ref;
      char[] chars = new char[arr.items.length];
      for (int i = 0; i < arr.items.length; i++) {
        chars[i] = ((Character) arr.items[i]);
      }
      System.out.print(new String(chars));
    });
    Heap.registerMethod("java/io/PrintStream_print_(Z)V", frame -> {
      int val = frame.popInt();
      frame.popRef();
      System.out.print(val == 1);
    });
    Heap.registerMethod("java/io/PrintStream_print_(C)V", frame -> {
      int val = frame.popInt();
      frame.popRef();
      System.out.print(((char) val));
    });
    Heap.registerMethod("java/io/PrintStream_print_(B)V", frame -> {
      int val = frame.popInt();
      frame.popRef();
      System.out.print(((byte) val));
    });
    Heap.registerMethod("java/io/PrintStream_print_(S)V", frame -> {
      int val = frame.popInt();
      frame.popRef();
      System.out.print(((short) val));
    });
    Heap.registerMethod("java/io/PrintStream_print_(I)V", frame -> {
      Object val = frame.popInt();
      frame.popRef();
      System.out.print(val);
    });
    Heap.registerMethod("java/io/PrintStream_print_(F)V", frame -> {
      float val = frame.popFloat();
      frame.popRef();
      System.out.print((val));
    });
    Heap.registerMethod("java/io/PrintStream_print_(J)V", frame -> {
      long val = frame.popLong();
      frame.popRef();
      System.out.print(val);
    });
    Heap.registerMethod("java/io/PrintStream_print_(D)V", frame -> {
      double val = frame.popDouble();
      frame.popRef();
      System.out.print(val);
    });
    Heap.registerMethod("java/io/PrintStream_print_(Ljava/lang/Object;)V", frame -> {
      Object val = frame.popRef();
      frame.popRef();
      System.out.print(val);
    });
  }
}
