package com.gxk.jvm.interpret;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Thread;
import com.gxk.jvm.rtda.heap.KArray;
import com.gxk.jvm.rtda.heap.KMethod;

public class Interpreter {

  public void interpret(KMethod method) {
    interpret(method, null);
  }

  public void interpret(KMethod method, String[] args) {
    Thread thread = new Thread(1024);
    Frame frame = new Frame(method, thread);
    if (args != null) {
      KArray array = new KArray(null, args);
      frame.localVars.setRef(0, array);
    }

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
      try {
        inst.execute(frame);
      } catch (Exception e) {
        e.printStackTrace();
      }
//      debugAfter(inst, frame);

      if (thread.empty()) {
        break;
      }
    }
  }

  void debugBefore(Instruction inst, Frame frame) {
    System.out.println("==============================");
    System.out.println(inst.getClass());
    frame.debug();
  }

  void debugAfter(Instruction inst, Frame frame) {
    System.out.println(inst.getClass());
    frame.debug();
    System.out.println("==============================");
  }

  public void initVm(KMethod vmClinit) {
    Thread thread = new Thread(1024);
    Frame frame = new Frame(vmClinit, thread);
    vmClinit.clazz.setStaticInit(1);
    thread.pushFrame(frame);
    frame.setOnPop(() -> vmClinit.clazz.setStaticInit(2));

    loop(thread);
  }
}
