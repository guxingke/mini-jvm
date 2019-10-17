package com.gxk.jvm.classloader;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.Field;
import com.gxk.jvm.classfile.Method;
import com.gxk.jvm.classfile.attribute.Code;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KField;
import com.gxk.jvm.rtda.heap.KMethod;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Classloader {

  public static void loadClass(String name, Entry entry) {
    KClass clazz = doLoadClass(name, entry);
    doRegister(clazz);
  }

  public static void doRegister(KClass clazz) {
    Heap.registerClass(clazz.name, clazz);
    for (KMethod method : clazz.getMethods()) {
      Heap.registerMethod(method.descriptor, method);
    }
  }

  public static KClass doLoadClass(String name, Entry entry) {
    ClassFile clazz = entry.findClass(name);
    return doLoadClass(name, clazz);
  }

  public static KClass doLoadClass(String name, ClassFile classFile) {
    List<KMethod> methods = Arrays.stream(classFile.methods.methods).map(Classloader::map).collect(Collectors.toList());
    List<KField> fields = Arrays.stream(classFile.fields.fields).map(Classloader::map).collect(Collectors.toList());
    return new KClass(name, methods, fields);
  }

  public static KMethod map(Method cfMethod) {
    Code code = cfMethod.getCode();
    return new KMethod(cfMethod.accessFlags, cfMethod.name, cfMethod.descriptor.descriptor, code.getMaxStacks(), code.getMaxLocals(), code.getInstructions());
  }

  public static KField map(Field field) {
    return new KField(field.getName(), field.getDescriptor().descriptor);
  }
}
