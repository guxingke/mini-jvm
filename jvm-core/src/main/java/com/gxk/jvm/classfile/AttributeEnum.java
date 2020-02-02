package com.gxk.jvm.classfile;

public abstract class AttributeEnum {

  // Five attributes are critical to correct interpretation of the class file by the Java Virtual Machine:
  public static final String ConstantValue = "ConstantValue";
  public static final String Code = "Code";
  public static final String StackMapTable = "StackMapTable";
  public static final String Exceptions = "Exceptions";
  public static final String BootstrapMethods = "BootstrapMethods";

  // Twelve attributes are critical to correct interpretation of the class file by the class libraries of the Java SE platform:
  public static final String InnerClasses = "InnerClasses";
  public static final String EnclosingMethod = "EnclosingMethod";
  public static final String Synthetic = "Synthetic";
  public static final String Signature = "Signature";
  public static final String RuntimeVisibleAnnotations = "RuntimeVisibleAnnotations";
  public static final String RuntimeInvisibleAnnotations = "RuntimeInvisibleAnnotations";
  public static final String RuntimeVisibleParameterAnnotations = "RuntimeVisibleParameterAnnotations";
  public static final String RuntimeInvisibleParameterAnnotations = "RuntimeInvisibleParameterAnnotations";
  public static final String RuntimeVisibleTypeAnnotations = "RuntimeVisibleTypeAnnotations";
  public static final String RuntimeInvisibleTypeAnnotations = "RuntimeInvisibleTypeAnnotations";
  public static final String AnnotationDefault = "AnnotationDefault";
  public static final String MethodParameters = "MethodParameters";

  //  Six attributes are not critical to correct interpretation of the class file by either the Java Virtual Machine or the class libraries of the Java SE platform, but are useful for tools:
  public static final String SourceFile = "SourceFile";
  public static final String SourceDebugExtension = "SourceDebugExtension";
  public static final String LineNumberTable = "LineNumberTable";
  public static final String LocalVariableTable = "LocalVariableTable";
  public static final String LocalVariableTypeTable = "LocalVariableTypeTable";
  public static final String Deprecated = "Deprecated";
}
