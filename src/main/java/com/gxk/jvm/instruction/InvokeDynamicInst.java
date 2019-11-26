package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    Map<Integer, Instruction> map = new HashMap<>();
    map.put(0, new InvokeStaticInst(bsTargetClass, bsTargetMethod, "()V"));
    map.put(3, new ReturnInst());

    lcMehods.add(new KMethod(1, methodName, method.getDescriptor(), 0, 1, map));

    KClass lcClazz = new KClass(lcname, "java/lang/Object", new ArrayList<>(), lcMehods, new ArrayList<>(), frame.method.clazz.classLoader);

    KObject kObject = lcClazz.newObject();
    frame.pushRef(kObject);
  }
}

