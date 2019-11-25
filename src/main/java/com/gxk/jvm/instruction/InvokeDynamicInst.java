package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class InvokeDynamicInst implements Instruction {

  public final String methodName;
  public final String methodDescriptor;

  public final String bsTargetClass;
  public final String bsTargetMethod;

  @Override
  public int offset() {
    return 5;
  }

  @Override
  public void execute(Frame frame) {
    KClass clazz = Heap.findClass(bsTargetClass);
    KMethod method = clazz.getMethod(bsTargetMethod, "()V");

    String lcname = frame.method.clazz.getName() + "$" + frame.method.getName() + "$" + bsTargetClass + "$" + bsTargetMethod;
    List<KMethod> lcMehods = new ArrayList<>();
    lcMehods.add(new KMethod(method.getAccessFlags(), methodName, method.getDescriptor(), method.getMaxStacks(), method.getMaxLocals(), method.getInstructionMap()));
    KClass lcClazz = new KClass(lcname, "java/lang/Object", new ArrayList<>(), lcMehods, new ArrayList<>(), frame.method.clazz.classLoader);

    KObject kObject = lcClazz.newObject();
    frame.pushRef(kObject);
  }
}

