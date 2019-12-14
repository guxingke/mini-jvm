package com.gxk.jvm.classloader;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.Field;
import com.gxk.jvm.classfile.Interface;
import com.gxk.jvm.classfile.Method;
import com.gxk.jvm.classfile.attribute.BootstrapMethods;
import com.gxk.jvm.classfile.attribute.Code;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KField;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.NativeMethod;
import com.gxk.jvm.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassLoader {

  private final String name;
  private final Entry entry;

  public ClassLoader(String name, Entry entry) {
    this.name = name;
    this.entry = entry;
  }

  public KClass loadClass(String name) {
    KClass cache = Heap.findClass(name);
    if (cache != null) {
      return cache;
    }

    KClass clazz = doLoadClass(name);
    doRegister(clazz);

    return clazz;
  }

  public void doRegister(KClass clazz) {
    Heap.registerClass(clazz.name, clazz);
    for (KMethod method : clazz.methods) {
      if (method.isNative()) {
        String key = String.format("%s_%s_%s", method.clazz.name, method.name, method.descriptor);
        NativeMethod nm = Heap.findMethod(key);
        if (nm == null) {
          System.err.println("not found native method " + key + " " + method);
        }
      }
    }
  }

  public KClass doLoadClass(String name) {
    ClassFile clazz = entry.findClass(name);
    KClass kClass = doLoadClass(name, clazz);

    // superclass
    if (kClass.superClassName != null) {
      kClass.setSuperClass(this.loadClass(kClass.superClassName));
    }


    return kClass;
  }

  public KClass doLoadClass(String name, ClassFile classFile) {
    List<KMethod> methods = Arrays.stream(classFile.methods.methods).map(this::map).collect(Collectors.toList());
    List<KField> fields = Arrays.stream(classFile.fields.fields).map(this::map).collect(Collectors.toList());

    // field init
    fields.forEach(it -> {
      switch (it.descriptor) {
        case "Z":
        case "C":
        case "B":
        case "S":
        case "I":
          it.val = new Slot[] {new Slot(0, Slot.INT)};
        case "F":
          it.val = new Slot[] {new Slot(0, Slot.FLOAT)};
          break;
        case "D":
          it.val = new Slot[] {new Slot(0,Slot.DOUBLE_HIGH), new Slot(0, Slot.DOUBLE_LOW)};
        case "J":
          it.val = new Slot[] {new Slot(0,Slot.LONG_HIGH), new Slot(0, Slot.LONG_LOW)};
          break;
        default:
          it.val = new Slot[] {new Slot(null)};
      }
    });


    int scIdx = classFile.superClass;
    String superClassName = null;
    if (scIdx != 0) {
      superClassName = Utils.getClassName(classFile.cpInfo, scIdx);
    }

    List<String> interfaceNames = new ArrayList<>();
    if (classFile.interfaces.interfaces.length != 0) {
      interfaceNames = Arrays.stream(classFile.interfaces.interfaces).map(Interface::getName).collect(Collectors.toList());
    }

    BootstrapMethods bootstrapMethods = classFile.getBootstrapMethods();

    return new KClass(name, superClassName, interfaceNames, methods, fields, bootstrapMethods, classFile.cpInfo, this);
  }

  public KMethod map(Method cfMethod) {
    Code code = cfMethod.getCode();
    if (code == null) {
      return new KMethod(cfMethod.accessFlags, cfMethod.name, cfMethod.descriptor.descriptor, 0, 0, null, null);
    }
    return new KMethod(cfMethod.accessFlags, cfMethod.name, cfMethod.descriptor.descriptor, code.maxStacks, code.maxLocals, code.getInstructions(), code.exceptionTable);
  }

  public KField map(Field field) {
    return new KField(field.accessFlags, field.name, field.descriptor.descriptor);
  }
}
