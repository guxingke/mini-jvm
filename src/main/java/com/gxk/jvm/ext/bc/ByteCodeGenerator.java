package com.gxk.jvm.ext.bc;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.ClassReader;
import com.gxk.jvm.classfile.Method;
import com.gxk.jvm.classfile.attribute.Code;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.heap.KMethod;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ByteCodeGenerator {

  public static  void gen(String clazzPath, String methodName) {
    Path path = Paths.get(clazzPath);
    if (!path.toFile().exists() || !path.toFile().isFile()) {
      System.out.println("class missing, or illegal path");
      return;
    }

    ClassFile cf = null;
    try {
      cf = ClassReader.read(path);
    } catch (IOException e) {
      System.out.println("parse class err, " + path);
      return;
    }

    Method target = null;
    for (Method method : cf.methods.methods) {
      if (method.name.equals(methodName)) {
        target = method;
      }
    }

    if (target == null) {
      System.out.println("not found method, " + methodName);
      return;
    }

    KMethod method = map(target);

    String header = "main " + method.getMaxStacks() + " " + method.getMaxLocals() + " " + method.getArgs().size();
    System.out.println(header);

    List<Integer> keys = method.getInstructionMap().keySet().stream().sorted().collect(Collectors.toList());
    for (Integer key : keys) {
      Instruction instruction = method.getInstructionMap().get(key);
      System.out.println(key + " " + instruction.format());
    }
  }

  private static KMethod map(Method cfMethod) {
    Code code = cfMethod.getCode();
    if (code == null) {
      return new KMethod(cfMethod.accessFlags, cfMethod.name, cfMethod.descriptor.descriptor, 0, 0, null);
    }
    return new KMethod(cfMethod.accessFlags, cfMethod.name, cfMethod.descriptor.descriptor, code.getMaxStacks(), code.getMaxLocals(), code.getInstructions());
  }
}
