package com.gxk.jvm.classfile;

import com.gxk.jvm.classfile.attribute.BootstrapMethods;
import lombok.AllArgsConstructor;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.1">
 * </a>https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.1</a>
 * <p>
 */
@AllArgsConstructor
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
