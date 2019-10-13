package com.gxk.jvm.classloader;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.Method;
import com.gxk.jvm.classfile.attribute.Code;
import com.gxk.jvm.classpath.Entry;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Classloader {

  public static void loadClass(String name, Entry entry, Heap heap) {
    KClass clazz = doLoadClass(name, entry);
    heap.registerClass(clazz.name, clazz);
    for (KMethod method : clazz.getMethods()) {
      heap.registerMethod(method.descriptor, method);
    }
  }

  public static KClass doLoadClass(String name, Entry entry) {
    ClassFile clazz = entry.findClass(name);
    return doLoadClass(name, clazz);
  }

  public static KClass doLoadClass(String name, ClassFile classFile) {
    List<KMethod> methods = Arrays.stream(classFile.methods.methods).map(Classloader::map).collect(Collectors.toList());
    return new KClass(name, methods);
  }

  public static KMethod map(Method cfMethod) {
    Code code = cfMethod.getCode();
    return new KMethod(cfMethod.accessFlags, cfMethod.name, cfMethod.descriptor.descriptor, code.getMaxStacks(), code.getMaxLocals(), code.getInstructions());
  }
}
