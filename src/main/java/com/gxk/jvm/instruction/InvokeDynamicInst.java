package com.gxk.jvm.instruction;

import com.gxk.jvm.classfile.attribute.BootstrapMethods;
import com.gxk.jvm.classfile.cp.MethodHandle;
import com.gxk.jvm.classfile.cp.MethodType;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.LocalVars;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.Stack;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.util.Utils;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class InvokeDynamicInst implements Instruction {

  public final String methodName;
  public final String methodDescriptor;

  public final int bsIdx;

  @Override
  public int offset() {
    return 5;
  }

  @Override
  public void execute(Frame frame) {
    BootstrapMethods bootstrapMethods = frame.method.clazz.bootstrapMethods;
    if (bootstrapMethods == null) {
      throw new IllegalStateException();
    }

    BootstrapMethods.BootstrapMethod bootstrapMethod = bootstrapMethods.methods[bsIdx];
    Integer argsRef = bootstrapMethod.getArgsRefs()[1];
    MethodHandle info = (MethodHandle) frame.method.clazz.constantPool.infos[argsRef - 1];
    String bsTargetClass = Utils.getClassNameByMethodDefIdx(frame.method.clazz.constantPool, info.referenceIndex);
    String bsTargetMethod = Utils.getMethodNameByMethodDefIdx(frame.method.clazz.constantPool, info.referenceIndex);

    Integer descRef0 = bootstrapMethod.getArgsRefs()[0];
    MethodType methodType0= (MethodType) frame.method.clazz.constantPool.infos[descRef0 - 1];
    String bstMethodDesc0 = Utils.getString(frame.method.clazz.constantPool, methodType0.descriptorIndex);

    Integer descRef = bootstrapMethod.getArgsRefs()[2];
    MethodType methodType= (MethodType) frame.method.clazz.constantPool.infos[descRef - 1];
    String bstMethodDesc = Utils.getString(frame.method.clazz.constantPool, methodType.descriptorIndex);

    KClass clazz = Heap.findClass(bsTargetClass);
    KMethod method = clazz.getMethod(bsTargetMethod, bstMethodDesc);
    int maxLocals = method.getMaxLocals();

    String lcname = frame.method.clazz.getName() + "$" + frame.method.getName() + "$" + bsTargetClass + "$" + bsTargetMethod;
    List<KMethod> lcMehods = new ArrayList<>();
    KMethod lm = new KMethod(1, methodName, bstMethodDesc0, method.getMaxStacks(), maxLocals + 1, null);
    lcMehods.add(lm);

    String format = String.format("%s_%s_%s", lcname, lm.name, lm.descriptor);
    Heap.registerMethod(format, (f) -> {
      LocalVars lv = f.getLocalVars();
      Slot[] slots = lv.getSlots();
      Slot[] newSlots = new Slot[0];
      if (slots.length > 1) {
        newSlots = new Slot[slots.length - 1];
        System.arraycopy(slots, 1, newSlots, 0, newSlots.length);
      }

      KClass bsc= Heap.findClass(bsTargetClass);
      if (bsc== null) {
        bsc = frame.method.clazz.getClassLoader().loadClass(bsTargetClass);
      }

      if (!bsc.isStaticInit()) {
        KMethod cinit = bsc.getClinitMethod();
        if (cinit == null) {
          throw new IllegalStateException();
        }

        Frame newFrame = new Frame(cinit, frame.thread);
        bsc.setStaticInit(1);
        KClass finalClass = bsc;
        newFrame.setOnPop(() -> finalClass.setStaticInit(2));
        frame.thread.pushFrame(newFrame);

        frame.nextPc = frame.thread.getPc();
        return;
      }

      KMethod bsm = bsc.getMethod(bsTargetMethod, bstMethodDesc);

      if (bsm == null) {
        throw new IllegalStateException();
      }

      Frame newFrame = new Frame(bsm, new LocalVars(newSlots), f.thread);
      f.thread.pushFrame(newFrame);
      f.nextPc = frame.thread.getPc();
    });

    KClass lcClazz = new KClass(lcname, "java/lang/Object", new ArrayList<>(), lcMehods, new ArrayList<>(), null, null, frame.method.clazz.classLoader);

    KObject kObject = lcClazz.newObject();
    frame.pushRef(kObject);
  }
}

