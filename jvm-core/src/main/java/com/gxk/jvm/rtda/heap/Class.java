package com.gxk.jvm.rtda.heap;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.ConstantPool;
import com.gxk.jvm.classfile.attribute.BootstrapMethods;
import com.gxk.jvm.classloader.ClassLoader;
import com.gxk.jvm.rtda.Frame;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

public class Class {

  public final int accessFlags;
  public final String name;
  public final String superClassName;
  public final List<String> interfaceNames;
  public final List<Method> methods;
  public final List<Field> fields;
  public final BootstrapMethods bootstrapMethods;
  public final ConstantPool constantPool;
  public final ClassLoader classLoader;
  public final ClassFile classFile;

  private Class superClass;
  private List<Class> interfaces;
  public int stat = 0;

  private Instance runtimeClass;

  public Class(int accessFlags, String name, ClassLoader classLoader, ClassFile classFile) {
    this.accessFlags = accessFlags;
    this.name = name;
    this.classFile = classFile;
    this.superClassName = "java/lang/Object";
    this.interfaceNames = new ArrayList<>();
    this.interfaces = new ArrayList<>();
    this.bootstrapMethods = null;
    this.constantPool = null;
    this.classLoader = classLoader;
    this.methods = new ArrayList<>();
    this.fields = new ArrayList<>();
    this.stat = 2;
  }

  public Class(int accessFlags, String name, ClassLoader classLoader) {
    this.accessFlags = accessFlags;
    this.name = name;
    this.classFile = null;
    this.superClassName = null;
    this.interfaceNames = new ArrayList<>();
    this.interfaces = new ArrayList<>();
    this.bootstrapMethods = null;
    this.constantPool = null;
    this.classLoader = classLoader;
    this.methods = new ArrayList<>();
    this.fields = new ArrayList<>();
    this.stat = 2;
  }

  public Class(
      int accessFlags,
      String name,
      String superClassName,
      List<String> interfaceNames,
      List<Method> methods,
      List<Field> fields,
      BootstrapMethods bootstrapMethods,
      ConstantPool constantPool,
      ClassLoader classLoader,
      ClassFile classFile) {
    this.accessFlags = accessFlags;
    this.name = name;
    this.superClassName = superClassName;
    this.interfaceNames = interfaceNames;
    this.classFile = classFile;
    this.interfaces = new ArrayList<>();
    this.methods = methods;
    this.fields = fields;
    this.bootstrapMethods = bootstrapMethods;
    this.constantPool = constantPool;
    this.classLoader = classLoader;

    methods.forEach(it -> it.clazz = this);
  }

  public Method getMainMethod() {
    for (Method method : methods) {
      if (Objects.equals("main", method.name)) {
        return method;
      }
    }
    return null;
  }

  public Method getClinitMethod() {
    return getMethod("<clinit>", "()V");
  }

  public Method getMethod(String name, String descriptor) {
    for (Method method : methods) {
      if (Objects.equals(method.name, name) && Objects.equals(method.descriptor, descriptor)) {
        return method;
      }
    }

    for (Class inter : this.interfaces) {
      Method method = inter.getMethod(name, descriptor);
      if (method != null) {
        return method;
      }
    }

    if (this.superClass == null) {
      return null;
    }
    return this.superClass.getMethod(name, descriptor);
  }

  public Method getLambdaMethod(String name) {
    for (Method method : methods) {
      if (Objects.equals(method.name, name)) {
        return method;
      }
    }
    return null;
  }

  public Field getField(String fieldName, String fieldDescriptor) {
    for (Field field : fields) {
      if (Objects.equals(field.name, fieldName) && Objects
          .equals(field.descriptor, fieldDescriptor)) {
        return field;
      }
    }
    return null;
  }

  public Field getField(String fieldName) {
    for (Field field : fields) {
      if (Objects.equals(field.name, fieldName)) {
        return field;
      }
    }
    return null;
  }

  public void setSuperClass(Class superClass) {
    this.superClass = superClass;
  }

  public Class getSuperClass() {
    return this.superClass;
  }

  public Instance newInstance() {
    List<Field> newFields = new ArrayList<>();
    for (Field field : fields) {
      newFields.add(this.map(field));
    }
    Instance object = new Instance(newFields, this);
    if (this.superClass != null) {
      object.setSuperInstance(this.superClass.newInstance());
    }
    return object;
  }

  public LambdaObject newLambdaObject(List<Object> args) {
    return new LambdaObject(this, args);
  }

  private Field map(Field source) {
    if (source.isStatic()) {
      return source;
    }
    final Field field = new Field(source.accessFlags, source.name, source.descriptor);
    field.init();
    return field;
  }

  public boolean getStat() {
    return this.stat > 0;
  }

  public void setStat(int level) {
    this.stat = level;
  }

  public Class getUnStaticInitSuperClass() {
    if (!this.getStat()) {
      return this;
    }
    if (this.superClass == null) {
      return null;
    }
    return this.superClass.getUnStaticInitSuperClass();
  }

  public void setInterfaces(List<Class> interfaces) {
    this.interfaces = interfaces;
  }

  public List<Class> getInterfaces() {
    return interfaces;
  }

  @Override
  public String toString() {
    return "KClass{" +
        "name='" + name + '\'' +
        ", superClassName='" + superClassName + '\'' +
        ", methods=" + methods.size() +
        ", fields=" + fields.size() +
        ", classLoader=" + classLoader.getClass().getName() +
        ", superClass=" + (superClass == null ? "null" : superClass.name) +
        ", staticInit=" + stat +
        '}';
  }

  public void interfaceInit(Frame frame) {
    List<Class> interfaces = new ArrayList<>();
    for (String interfaceName : this.interfaceNames) {
      Class tmp = Heap.findClass(interfaceName);
      if (tmp == null) {
        tmp = frame.method.clazz.classLoader.loadClass(interfaceName);
      }

      tmp.interfaceInit(frame);

      interfaces.add(tmp);
      if (!tmp.getStat()) {
        Method cinit = tmp.getClinitMethod();
        if (cinit == null) {
          throw new IllegalStateException();
        }

        Frame newFrame = new Frame(cinit);
        tmp.setStat(1);
        Class finalClass = tmp;
        newFrame.setOnPop(() -> finalClass.setStat(2));
        frame.thread.pushFrame(newFrame);
        frame.nextPc = frame.getPc();
      }
    }
    this.setInterfaces(interfaces);
  }

  public boolean is(String clazz) {
    if (this.name.equals(clazz)) {
      return true;
    }
    for (String interfaceName : this.interfaceNames) {
      if (Objects.equals(interfaceName, clazz)) {
        return true;
      }
    }
    if (this.superClass != null) {
      return this.superClass.is(clazz);
    }
    return false;
  }

  public boolean isInterface() {
    return (accessFlags & 0x0200) != 0;
  }

  public Instance getRuntimeClass() {
    return runtimeClass;
  }

  public void setRuntimeClass(Instance runtimeClass) {
    this.runtimeClass = runtimeClass;
  }

  public boolean isPrimitive() {
    if (name.equals("java/lang/Character")) {
      return true;
    }
    System.out.println("is primitive ? " + name);
    return false;
  }

  public String getSource() {
    return classFile.getSourceFile();
  }
}
