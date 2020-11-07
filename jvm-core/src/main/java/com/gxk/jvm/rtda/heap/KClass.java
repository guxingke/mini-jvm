package com.gxk.jvm.rtda.heap;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.ConstantPool;
import com.gxk.jvm.classfile.attribute.BootstrapMethods;
import com.gxk.jvm.classloader.ClassLoader;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

public class KClass {

  public final int accessFlags;
  public final String name;
  public final String superClassName;
  public final List<String> interfaceNames;
  public final List<KMethod> methods;
  public final List<KField> fields;
  public final BootstrapMethods bootstrapMethods;
  public final ConstantPool constantPool;
  public final ClassLoader classLoader;
  public final ClassFile classFile;

  private KClass superClass;
  private List<KClass> interfaces;
  private int staticInit = 0;

  private KObject runtimeClass;

  public KClass(int accessFlags, String name, ClassLoader classLoader, ClassFile classFile) {
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
    this.staticInit = 2;
  }

  public KClass(int accessFlags, String name, ClassLoader classLoader) {
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
    this.staticInit = 2;
  }

  public KClass(
      int accessFlags,
      String name,
      String superClassName,
      List<String> interfaceNames,
      List<KMethod> methods,
      List<KField> fields,
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

  public KMethod getMainMethod() {
    for (KMethod method : methods) {
      if (Objects.equals("main", method.name)) {
        return method;
      }
    }
    return null;
  }

  public KMethod getClinitMethod() {
    return getMethod("<clinit>", "()V");
  }

  public KMethod getMethod(String name, String descriptor) {
    for (KMethod method : methods) {
      if (Objects.equals(method.name, name) && Objects.equals(method.descriptor, descriptor)) {
        return method;
      }
    }

    for (KClass inter : this.interfaces) {
      KMethod method = inter.getMethod(name, descriptor);
      if (method != null) {
        return method;
      }
    }

    if (this.superClass == null) {
      return null;
    }
    return this.superClass.getMethod(name, descriptor);
  }

  public KMethod getLambdaMethod(String name) {
    for (KMethod method : methods) {
      if (Objects.equals(method.name, name)) {
        return method;
      }
    }
    return null;
  }

  public KField getField(String fieldName, String fieldDescriptor) {
    for (KField field : fields) {
      if (Objects.equals(field.name, fieldName) && Objects
          .equals(field.descriptor, fieldDescriptor)) {
        return field;
      }
    }
    return null;
  }

  public KField getField(String fieldName) {
    for (KField field : fields) {
      if (Objects.equals(field.name, fieldName)) {
        return field;
      }
    }
    return null;
  }

  public void setSuperClass(KClass superClass) {
    this.superClass = superClass;
  }

  public KClass getSuperClass() {
    return this.superClass;
  }

  public KObject newObject() {
    List<KField> newFields = new ArrayList<>();
    for (KField field : fields) {
      newFields.add(this.map(field));
    }
    KObject object = new KObject(newFields, this);
    if (this.superClass != null) {
      object.setSuperObject(this.superClass.newObject());
    }
    return object;
  }

  public KLambdaObject newLambdaObject(List<Object> args) {
    return new KLambdaObject(this, args);
  }

  private KField map(KField source) {
    if (source.isStatic()) {
      return source;
    }
    switch (source.descriptor) {
      case "Z":
      case "C":
      case "B":
      case "S":
      case "I":
        return new KField(source.accessFlags, source.name, source.descriptor,
            new Slot[]{new Slot(0, Slot.INT)});
      case "F":
        return new KField(source.accessFlags, source.name, source.descriptor,
            new Slot[]{new Slot(0, Slot.FLOAT)});
      case "D":
        return new KField(source.accessFlags, source.name, source.descriptor,
            new Slot[]{new Slot(0, Slot.DOUBLE_HIGH), new Slot(0, Slot.DOUBLE_LOW)});
      case "J":
        return new KField(source.accessFlags, source.name, source.descriptor,
            new Slot[]{new Slot(0, Slot.LONG_HIGH), new Slot(0, Slot.LONG_LOW)});
      default:
        return new KField(source.accessFlags, source.name, source.descriptor,
            new Slot[]{new Slot(null)});
    }
  }

  public boolean isStaticInit() {
    return this.staticInit > 0;
  }

  public void setStaticInit(int level) {
    this.staticInit = level;
  }

  public KClass getUnStaticInitSuperClass() {
    if (!this.isStaticInit()) {
      return this;
    }
    if (this.superClass == null) {
      return null;
    }
    return this.superClass.getUnStaticInitSuperClass();
  }

  public void setInterfaces(List<KClass> interfaces) {
    this.interfaces = interfaces;
  }

  public List<KClass> getInterfaces() {
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
        ", staticInit=" + staticInit +
        '}';
  }

  public void interfaceInit(Frame frame) {
    List<KClass> interfaces = new ArrayList<>();
    for (String interfaceName : this.interfaceNames) {
      KClass tmp = Heap.findClass(interfaceName);
      if (tmp == null) {
        tmp = frame.method.clazz.classLoader.loadClass(interfaceName);
      }

      tmp.interfaceInit(frame);

      interfaces.add(tmp);
      if (!tmp.isStaticInit()) {
        KMethod cinit = tmp.getClinitMethod();
        if (cinit == null) {
          throw new IllegalStateException();
        }

        Frame newFrame = new Frame(cinit);
        tmp.setStaticInit(1);
        KClass finalKClass = tmp;
        newFrame.setOnPop(() -> finalKClass.setStaticInit(2));
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

  public KObject getRuntimeClass() {
    return runtimeClass;
  }

  public void setRuntimeClass(KObject runtimeClass) {
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
