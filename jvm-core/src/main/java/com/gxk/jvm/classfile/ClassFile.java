package com.gxk.jvm.classfile;

import com.gxk.jvm.classfile.attribute.BootstrapMethods;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.1">
 * </a>https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.1</a>
 * <p>
 */
public class ClassFile {

  public final int magic;
  public final int minorVersion;
  public final int majorVersion;
  public final int constantPoolSize;
  public final ConstantPool cpInfo;
  public final int accessFlags;
  public final int thisClass;
  public final int superClass;
  public final int interfacesCount;
  public final Interfaces interfaces;
  public final int fieldCount;
  public final Fields fields;
  public final int methodsCount;
  public final Methods methods;
  public final int attributesCount;
  public final Attributes attributes;


  // ext info
  private String source;

  public ClassFile(int magic, int minorVersion, int majorVersion, int constantPoolSize,
      ConstantPool cpInfo, int accessFlags, int thisClass, int superClass, int interfacesCount,
      Interfaces interfaces, int fieldCount, Fields fields, int methodsCount, Methods methods, int attributesCount,
      Attributes attributes) {
    this.magic = magic;
    this.minorVersion = minorVersion;
    this.majorVersion = majorVersion;
    this.constantPoolSize = constantPoolSize;
    this.cpInfo = cpInfo;
    this.accessFlags = accessFlags;
    this.thisClass = thisClass;
    this.superClass = superClass;
    this.interfacesCount = interfacesCount;
    this.interfaces = interfaces;
    this.fieldCount = fieldCount;
    this.fields = fields;
    this.methodsCount = methodsCount;
    this.methods = methods;
    this.attributesCount = attributesCount;
    this.attributes = attributes;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getSource() {
    return source;
  }

  public BootstrapMethods getBootstrapMethods() {
    for (Attribute attribute : attributes.attributes) {
      if (attribute instanceof BootstrapMethods) {
        return (BootstrapMethods) attribute;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return "ClassFile{" +
      "\n, magic=" + magic +
      "\n, minorVersion=" + minorVersion +
      "\n, majorVersion=" + majorVersion +
      "\n, constantPoolSize=" + constantPoolSize +
      "\n, cpInfo=" + cpInfo +
      "\n, accessFlags=" + accessFlags +
      "\n, thisClass=" + thisClass +
      "\n, superClass=" + superClass +
      "\n, interfacesCount=" + interfacesCount +
      "\n, interfaces=" + interfaces +
      "\n, fieldCount=" + fieldCount +
      "\n, fields=" + fields +
      "\n, methodsCount=" + methodsCount +
      "\n, methods=" + methods +
      "\n, attributesCount=" + attributesCount +
      "\n, attributes=" + attributes +
      "\n}";
  }
}
