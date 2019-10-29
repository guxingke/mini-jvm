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

public class Interpreter {

  public void interpret(KMethod method) {
    interpret(method, null);
  }

  public void interpret(KMethod method, String[] args) {
    Thread thread = new Thread(1024);
    Frame frame = new Frame(method, thread);
    if (args == null) {
      doInterpret(thread, frame);
      return;
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
    frame.localVars.setRef(0, array);

    doInterpret(thread, frame);
  }

  private void doInterpret(Thread thread, Frame frame) {
    thread.pushFrame(frame);
    loop(thread);
  }

  public void loop(Thread thread) {
    while (true) {
      Frame frame = thread.currentFrame();
      int pc = frame.nextPc;
      thread.setPc(pc);

      Instruction inst = frame.getInst(pc);
      frame.nextPc += inst.offset();

//      debugBefore(inst, frame);
      inst.execute(frame);
//      debugAfter(inst, frame);

      if (thread.empty()) {
        break;
      }
    }
  }

  void debugBefore(Instruction inst, Frame frame) {
    System.out.println(frame.thread.size() + " <>==============================");
    inst.debug();
    frame.debug();
  }

  void debugAfter(Instruction inst, Frame frame) {
    inst.debug();
    frame.debug();
    System.out.println(frame.thread.size() + " <>==============================");
  }
}
