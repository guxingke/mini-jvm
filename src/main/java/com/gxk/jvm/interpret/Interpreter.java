package com.gxk.jvm.interpret;

import com.gxk.jvm.classfile.CodeAttribute;
import com.gxk.jvm.classfile.CodeFromByte;
import com.gxk.jvm.classfile.MethodInfo;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Thread;

public class Interpreter {

  public void interpret(MethodInfo method) {
    CodeAttribute codeAttribute = method.code;

    int maxLocals = codeAttribute.maxLocals;
    int maxStacks = codeAttribute.maxStacks;
    CodeFromByte code = codeAttribute.code;

    Thread thread = new Thread(1024);
    Frame frame = new Frame(maxLocals, maxStacks, code, thread);
    thread.pushFrame(frame);

    loop(thread);
  }

  public void loop(Thread thread) {
    while (true) {
      Frame frame = thread.currentFrame();
      int pc = frame.nextPc;
      thread.setPc(pc);

      Instruction inst = frame.code.getInst(pc);
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
