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
import com.gxk.jvm.util.DebugContextHolder;
import com.gxk.jvm.util.EnvHolder;
import com.gxk.jvm.util.Logger;

import java.util.Objects;
import java.util.Scanner;

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
      obj.setField("value", "[C", new Slot[] {new Slot(args[i])});
      kargs[i] = obj;
    }
    KClass arrClazz = new KClass(1, "[java/lang/String", method.clazz.classLoader, null);
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
      while (superClazz != null) {
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
    if (EnvHolder.debug) {
      try {
        System.out.println("正在初始化jdb...");
        DebugContextHolder.scanner = new Scanner(System.in);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    do {
      Frame frame = thread.currentFrame();
      int pc = frame.nextPc;
      thread.setPc(pc);

      Instruction inst = frame.getInst(pc);
      traceBefore(inst, frame);

      frame.nextPc += inst.offset();
      if (EnvHolder.debug) {
        boolean cont = doDebug(inst, frame);
        if (!cont) {
          // restore
          frame.nextPc -= inst.offset();
          continue;
        }
      }

      inst.execute(frame);

    } while (!thread.empty());
  }

  private boolean doDebug(Instruction inst, Frame frame) {
    if (DebugContextHolder.next) {
      if (!inst.format().startsWith("invoke")) {
        return true;
      }
      DebugContextHolder.next = false;
    }

    Scanner scanner = DebugContextHolder.scanner;
    if (scanner == null) {
      Logger.error("reader init err in debug mode, debug mode closed");
      EnvHolder.debug = false;
      return false;
    }
    try {
      String promot = frame.thread.size() + " > ";
      System.out.print(promot);
      String line = scanner.nextLine();
      if (line == null || line.trim().isEmpty()) {
        return false;
      }

      String cmd = line.trim().toLowerCase();
      if (!DebugContextHolder.running && !(Objects.equals("run", cmd) || Objects.equals("help", cmd))) {
        System.out.println(String.format("在使用 'run' 命令启动 VM 前, 命令 '%s' 是无效的", line));
        return false;
      }
      switch (cmd) {
        case "run":
          System.out.println(String.format("运行 %s", DebugContextHolder.mainClass));
          DebugContextHolder.running = true;
          break;
        case "help":
        case "h":
          System.out.println("print help");
          break;
        case "env":
          debugBefore(inst, frame);
          break;
        case "step":
        case "s":
          DebugContextHolder.step = true;
          break;
        case "next":
        case "n":
          DebugContextHolder.next = true;
          break;
        case "list":
        case "ls":
          frame.method.instructionMap.forEach((key, val) -> {
            String prefix = "     ";
            if (frame.getPc() == key) {
              prefix = "==>  ";
            }
            System.out.println(prefix + key + " " + val.format());
          });
          break;
        default:
          System.out.println("unsupport " + cmd);
          break;
      }
      if (!DebugContextHolder.isContinue()) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }

  private void traceBefore(Instruction inst, Frame frame) {
    if (EnvHolder.verboseDebug) {
      debugBefore(inst, frame);
    }
    // verboseTrace
    if (EnvHolder.verboseTrace) {
      trace(inst, frame);
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
