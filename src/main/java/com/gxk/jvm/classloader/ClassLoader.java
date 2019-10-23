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
import com.gxk.jvm.rtda.heap.NativeMethod;
import com.gxk.jvm.util.Utils;

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
    for (KMethod method : clazz.getMethods()) {
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

    int scIdx = classFile.superClass;
    if (scIdx == 0) {
      return new KClass(name, null, methods, fields, this);
    }

    String superClassName = Utils.getClassName(classFile.cpInfo, scIdx);
    return new KClass(name, superClassName, methods, fields, this);
  }

  public KMethod map(Method cfMethod) {
    Code code = cfMethod.getCode();
    if (code == null) {
      return new KMethod(cfMethod.accessFlags, cfMethod.name, cfMethod.descriptor.descriptor, 0, 0, null);
    }
    return new KMethod(cfMethod.accessFlags, cfMethod.name, cfMethod.descriptor.descriptor, code.getMaxStacks(), code.getMaxLocals(), code.getInstructions());
  }

  public KField map(Field field) {
    return new KField(field.getName(), field.getDescriptor().descriptor);
  }
}
