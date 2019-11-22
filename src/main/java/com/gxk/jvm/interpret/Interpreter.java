package com.gxk.jvm.interpret;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Slot;
import com.gxk.jvm.rtda.Thread;
import com.gxk.jvm.rtda.heap.Heap;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import com.gxk.jvm.rtda.heap.KObject;
import com.gxk.jvm.util.EnvHolder;

public class Interpreter {

  public void interpret(KMethod method) {
    interpret(method, null);
  }

  public void interpret(KMethod method, String[] args) {
    Thread thread = new Thread(1024);
    Frame frame = new Frame(method, thread);
    if (args == null) {
      args = new String[0];
    }

    KObject[] kargs = new KObject[args.length];
    KClass strClazz = Heap.findClass("java/lang/String");
    for (int i = 0; i < kargs.length; i++) {
      KObject obj = new KObject(strClazz.getFields(), strClazz);
      obj.setField("value", "[C", new Slot[]{new Slot(args[i])});
      kargs[i] = obj;
    }
    KClass arrClazz = new KClass("[java/lang/String", method.clazz.classLoader);
    KArray array = new KArray(arrClazz, args);
    frame.setRef(0, array);

    doInterpret(thread, frame);
  }

  private void doInterpret(Thread thread, Frame frame) {
    thread.pushFrame(frame);

    KClass clazz = frame.method.clazz;
    if (clazz != null) {
      // super clazz static init
      KClass superClazz = clazz.getUnStaticInitSuperClass();
      while (superClazz!=null) {
        if (!superClazz.isStaticInit()) {
          // init
          KMethod cinit = superClazz.getMethod("<clinit>", "()V");
          if (cinit == null) {
            superClazz.setStaticInit(2);
            frame.nextPc = frame.thread.getPc();
            return;
          }

          Frame newFrame = new Frame(cinit, frame.thread);
          superClazz.setStaticInit(1);
          KClass finalKClass = superClazz;
          newFrame.setOnPop(() -> finalKClass.setStaticInit(2));
          frame.thread.pushFrame(newFrame);
        }
        superClazz = clazz.getUnStaticInitSuperClass();
      }
    }

    loop(thread);
  }

  public void loop(Thread thread) {
    while (true) {
      Frame frame = thread.currentFrame();
      int pc = frame.nextPc;
      thread.setPc(pc);

      Instruction inst = frame.getInst(pc);
      frame.nextPc += inst.offset();

      if (EnvHolder.verbose) {
        debugBefore(inst, frame);
      }
      inst.execute(frame);
      if (EnvHolder.verbose) {
        debugAfter(inst, frame);
      }

      if (thread.empty()) {
        break;
      }
    }
  }

  void debugBefore(Instruction inst, Frame frame) {
    String space = genSpace(frame.thread.size() * 2);
    System.out.println(space + frame.thread.size() + " <> " + frame.method.getName() + "_" + frame.method.getDescriptor() + " ============================== begin");
    inst.debug(space);
    frame.debug(space);
    System.out.println(space + "---------------------");
  }

  void debugAfter(Instruction inst, Frame frame) {
    String space = genSpace(frame.thread.size() * 2);
    System.out.println(space + "---------------------");
    inst.debug(space);
    frame.debug(space);
    System.out.println(space + frame.thread.size() + " <> " + frame.method.getName() + "_" + frame.method.getDescriptor() + " ==============================   end");
    System.out.println();
  }

  public String genSpace(int size) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < size; i++) {
      sb.append(" ");
    }
    return sb.toString();
  }
}
