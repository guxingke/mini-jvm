package com.gxk.jvm.interpret;

import com.gxk.jvm.classfile.CodeFromByte;
import com.gxk.jvm.classfile.CodeAttribute;
import com.gxk.jvm.classfile.MethodInfo;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.instruction.IreturnInst;
import com.gxk.jvm.instruction.ReturnInst;
import com.gxk.jvm.rtda.Env;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Thread;

public class Interpreter {

  public void interpret(MethodInfo method, Env env) {
    CodeAttribute codeAttribute = method.code;

    int maxLocals = codeAttribute.maxLocals;
    int maxStacks = codeAttribute.maxStacks;
    CodeFromByte code = codeAttribute.code;

    Thread thread = new Thread(1024);
    Frame frame = new Frame(maxLocals, maxStacks, thread, env);
    thread.pushFrame(frame);

    loop(thread, code);
  }

  private void loop(Thread thread, CodeFromByte code) {
    Frame frame = thread.popFrame();

    while (true) {
      int pc = frame.nextPc;
      thread.setPc(pc);

      Instruction inst = code.getInst(pc);
      frame.nextPc += inst.offset();

      inst.fetchOperands();
      inst.execute(frame);

      debug(inst, frame);

      if (inst instanceof IreturnInst || inst instanceof ReturnInst) {
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
