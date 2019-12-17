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
import com.gxk.jvm.util.Logger;
import sun.rmi.runtime.Log;

public class Interpreter {

  public void interpret(KMethod method) {
    interpret(method, null);
  }

  public void interpret(KMethod method, String[] args) {
    Thread thread = new Thread(1024);
    Frame frame = new Frame(method, thread);
    if (args == null) {
      this.doInterpret(thread, frame);
      return;
    }

    KObject[] kargs = new KObject[args.length];
    KClass strClazz = Heap.findClass("java/lang/String");
    for (int i = 0; i < kargs.length; i++) {
      KObject obj = new KObject(strClazz.fields, strClazz);
      obj.setField("value", "[C", new Slot[]{new Slot(args[i])});
      kargs[i] = obj;
    }
    KClass arrClazz = new KClass("[java/lang/String", method.clazz.classLoader, null);
    KArray array = new KArray(arrClazz, args);
    frame.setRef(0, array);

    doInterpret(thread, frame);
  }

  private void doInterpret(Thread thread, Frame frame) {
    thread.pushFrame(frame);

    KClass clazz = frame.method.clazz;
    if (clazz != null) {
      // super clazz static interfaceInit
      KClass superClazz = clazz.getUnStaticInitSuperClass();
      while (superClazz!=null) {
        if (!superClazz.isStaticInit()) {
          // interfaceInit
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

      if (EnvHolder.verboseDebug) {
        debugBefore(inst, frame);
      }
      // verboseTrace
      if (EnvHolder.verboseTrace) {
        trace(inst, frame);
      }
      inst.execute(frame);

      if (thread.empty()) {
        break;
      }
    }
  }

  private void trace(Instruction inst, Frame frame) {
    String space = genSpace((frame.thread.size() - 1) * 2);
    Logger.trace("{}{}", space, frame.thread.getPc() + " " + inst.format());
  }

  void debugBefore(Instruction inst, Frame frame) {
    String space = genSpace(frame.thread.size() * 2);
    Logger.debug(space + frame.thread.size() + " <> " + frame.method.name + "_" + frame.method.descriptor + " ==============================" + "\n");
    Logger.debug(inst.debug(space + frame.getPc() + " "));
    Logger.debug(frame.debugNextPc(space));
    Logger.debug(frame.debugLocalVars(space));
    Logger.debug(frame.debugOperandStack(space));
    Logger.debug(space + "---------------------");
    Logger.debug(space + "\n");
  }

  public String genSpace(int size) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < size; i++) {
      sb.append(" ");
    }
    return sb.toString();
  }
}
