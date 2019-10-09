package com.gxk.jvm.interpret;

import com.gxk.jvm.classfile.Code;
import com.gxk.jvm.classfile.CodeAttribute;
import com.gxk.jvm.classfile.MethodInfo;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.instruction.IreturnInst;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Thread;

public class Interpreter {

  public void interpret(MethodInfo method) {
    CodeAttribute codeAttribute = method.codeAttribute;

    int maxLocals = codeAttribute.maxLocals;
    int maxStacks = codeAttribute.maxStacks;
    Code code = codeAttribute.code;

    Thread thread = new Thread(1024);
    Frame frame = new Frame(maxLocals, maxStacks, thread);
    thread.pushFrame(frame);

    loop(thread, code);
  }

  private void loop(Thread thread, Code code) {
    Frame frame = thread.popFrame();

    while (true) {
      int pc = frame.nextPc;
      thread.setPc(pc);

      Instruction inst = code.getInst(pc);
      frame.nextPc += inst.offset();

      inst.fetchOperands();
      inst.execute(frame);

      debug(inst, frame);

      if (inst instanceof IreturnInst) {
        break;
      }
    }
  }

  void debug(Instruction inst, Frame frame) {
    System.out.println("==============================");
    System.out.println(inst.getClass());
    frame.debug();
//    System.out.println("==============================");
  }
}
