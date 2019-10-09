package com.gxk.jvm.classfile;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.1">
 * </a>https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.1</a>
 *
 * ClassFile { u4             magic; u2             minor_version; u2             major_version; u2
 * constant_pool_count; cp_info        constant_pool[constant_pool_count-1]; u2             access_flags; u2
 * this_class; u2             super_class; u2             interfaces_count; u2             interfaces[interfaces_count];
 * u2             fields_count; field_info     fields[fields_count]; u2             methods_count; method_info
 * methods[methods_count]; u2             attributes_count; attribute_info attributes[attributes_count]; }
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
