package com.gxk.jvm.classfile;

public enum AttributeEnum {
  // Five attributes are critical to correct interpretation of the class file by the Java Virtual Machine:
  ConstantValue,
  Code,
  StackMapTable,
  Exceptions,
  BootstrapMethods,

  // Twelve attributes are critical to correct interpretation of the class file by the class libraries of the Java SE platform:
  InnerClasses,
  EnclosingMethod,
  Synthetic,
  Signature,
  RuntimeVisibleAnnotations,
  RuntimeInvisibleAnnotations,
  RuntimeVisibleParameterAnnotations,
  RuntimeInvisibleParameterAnnotations,
  RuntimeVisibleTypeAnnotations,
  RuntimeInvisibleTypeAnnotations,
  AnnotationDefault,
  MethodParameters,

  //  Six attributes are not critical to correct interpretation of the class file by either the Java Virtual Machine or the class libraries of the Java SE platform, but are useful for tools:
  SourceFile,
  SourceDebugExtension,
  LineNumberTable,
  LocalVariableTable,
  LocalVariableTypeTable,
  Deprecated,
  ;
}
