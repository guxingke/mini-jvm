package com.gxk.jvm.instruction;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.ClassReader;
import com.gxk.jvm.classfile.CodeAttribute;
import com.gxk.jvm.classfile.CodeFromByte;
import com.gxk.jvm.classfile.Method;
import com.gxk.jvm.classfile.MethodInfo;
import com.gxk.jvm.classfile.attribute.Code;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Thread;
import com.gxk.jvm.rtda.heap.KMethod;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class InvokestaticInst implements Instruction {

  public final int methodRefIndex;

  public InvokestaticInst(int methodRefIndex) {
    this.methodRefIndex = methodRefIndex;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Frame frame2 = sum10Frame(frame.thread);
    frame.thread.pushFrame(frame2);
  }

  // TODO temp
  private Frame sum10Frame(Thread thread) {
    ClassFile cf = null;
    try {
      cf = ClassReader.read(Paths.get("example/Loop4.class"));
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    Method sum10 = cf.methods.methods[2];

    KMethod method = map(sum10);

    Frame frame = new Frame(method, thread);

    return frame;
  }

  private KMethod map(Method cfMethod) {
    Code code = cfMethod.getCode();
    return new KMethod(cfMethod.accessFlags, cfMethod.name, cfMethod.descriptor.descriptor, code.getMaxStacks(), code.getMaxLocals(), code.getInstructions());
  }
}

