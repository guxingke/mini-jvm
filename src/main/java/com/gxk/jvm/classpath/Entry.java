package com.gxk.jvm.classpath;

import com.gxk.jvm.classfile.ClassFile;

public interface Entry {

  ClassFile findClass(String name);
}
