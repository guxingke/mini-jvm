package com.gxk.jvm.ext.bc;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.ClassReader;
import com.gxk.jvm.classfile.MethodInfo;
import com.gxk.jvm.classfile.attribute.Code;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.heap.Method;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ByteCodeGenerator {

  public static void gen(String clazzPath, String methodName) {
    File file = new File(clazzPath);
    if (!file.exists() || !file.isFile()) {
      System.out.println("class missing, or illegal path");
      return;
    }

    ClassFile cf = null;
    try {
      cf = ClassReader.read(clazzPath);
    } catch (IOException e) {
      System.out.println("parse class err, " + clazzPath);
      return;
    }

    MethodInfo target = null;
    for (MethodInfo methodInfo : cf.methods.methodInfos) {
      if (methodInfo.name.equals(methodName)) {
        target = methodInfo;
      }
    }

    if (target == null) {
      System.out.println("not found method, " + methodName);
      return;
    }

    Method method = map(target);

    String header =
        "main " + method.maxStacks + " " + method.maxLocals + " " + method.getArgs().size();
    System.out.println(header);

    List<Integer> keys = method.instructionMap.keySet().stream().sorted()
        .collect(Collectors.toList());
    for (Integer key : keys) {
      Instruction instruction = method.instructionMap.get(key);
      System.out.println(key + " " + instruction.format());
    }
  }

  private static Method map(MethodInfo cfMethodInfo) {
    Code code = cfMethodInfo.getCode();
    if (code == null) {
      return new Method(cfMethodInfo.accessFlags, cfMethodInfo.name, cfMethodInfo.descriptor.descriptor, 0, 0,
          null, null, null);
    }
    return new Method(cfMethodInfo.accessFlags, cfMethodInfo.name, cfMethodInfo.descriptor.descriptor,
        code.maxStacks, code.maxLocals, code.getInstructions(), null, null);
  }
}
