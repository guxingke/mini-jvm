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

      inst.fetchOperands();
      inst.execute(frame);

//      debug(inst, frame);

      if (thread.empty()) {
        break;
      }
    }
  }

  void debug(Instruction inst, Frame frame) {
    System.out.println("==============================");
    System.out.println(inst.getClass());
    frame.debug();
  }
}
