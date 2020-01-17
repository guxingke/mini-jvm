package com.gxk.jvm.ext.bc;

import com.gxk.jvm.instruction.BiPushInst;
import com.gxk.jvm.instruction.GotoInst;
import com.gxk.jvm.instruction.IAddInst;
import com.gxk.jvm.instruction.IConst0Inst;
import com.gxk.jvm.instruction.IConst1Inst;
import com.gxk.jvm.instruction.IIncInst;
import com.gxk.jvm.instruction.ILoad0Inst;
import com.gxk.jvm.instruction.ILoad1Inst;
import com.gxk.jvm.instruction.ILoad2Inst;
import com.gxk.jvm.instruction.IStore0Inst;
import com.gxk.jvm.instruction.IStore1Inst;
import com.gxk.jvm.instruction.IStore2Inst;
import com.gxk.jvm.instruction.IfICmpGtInst;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.interpret.Interpreter;
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Thread;
import com.gxk.jvm.rtda.heap.KMethod;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ByteCodeInterpreter {

  public static void interpreter(String file, Integer... args) {
    // parse
    List<String> rawLines;
    try {
      rawLines = Files.readAllLines(Paths.get(file));
    } catch (IOException e) {
      System.out.println("file not found ?");
      return;
    }

    if (rawLines.isEmpty()) {
      System.out.println("empty file");
    }

    List<String> lines = rawLines.stream()
        .map(it -> it.replaceAll(" +", " ")) // 多个空格合并
        .filter(it -> it.length() > 0)
        .collect(Collectors.toList());

    String header = lines.get(0);
    String[] split = header.split(" ");
    int stacks = Integer.parseInt(split[1]);
    int locals = Integer.parseInt(split[2]);
    int argsCnt = Integer.parseInt(split[3]);

    // interfaceInit method args
    Map<Integer, Integer> methodArgs = new HashMap<>(argsCnt);
    for (int i = 0; i < argsCnt; i++) {
      methodArgs.put(i, 0);
    }
    if (args.length > 0) {
      for (int i = 0; i < Math.min(args.length, argsCnt); i++) {
        methodArgs.put(i, args[i]);
      }
    }

    Map<Integer, Instruction> instructionMap = new HashMap<>();
    for (int i = 1; i < lines.size(); i++) {
      String raw = lines.get(i);
      String[] terms = raw.split(" ");
      int pc = Integer.parseInt(terms[0]);
      String inst = terms[1];

      Instruction instruction = null;
      switch (inst.toLowerCase()) {
        case "iconst_0":
          instruction = new IConst0Inst();
          break;
        case "iconst_1":
          instruction = new IConst1Inst();
          break;
        case "istore_0":
          instruction = new IStore0Inst();
          break;
        case "istore_1":
          instruction = new IStore1Inst();
          break;
        case "istore_2":
          instruction = new IStore2Inst();
          break;
        case "iload_0":
          instruction = new ILoad0Inst();
          break;
        case "iload_1":
          instruction = new ILoad1Inst();
          break;
        case "iload_2":
          instruction = new ILoad2Inst();
          break;
        case "bipush":
          instruction = new BiPushInst(Byte.parseByte(terms[2]));
          break;
        case "if_icmpgt":
          instruction = new IfICmpGtInst(Integer.parseInt(terms[2]));
          break;
        case "iadd":
          instruction = new IAddInst();
          break;
        case "iinc":
          instruction = new IIncInst(Integer.parseInt(terms[2]), Integer.parseInt(terms[3]));
          break;
        case "goto":
          instruction = new GotoInst(Short.parseShort(terms[2]));
          break;
        case "ireturn":
          instruction = new IReturnInst();
          break;
      }

      if (instruction == null) {
        System.out.println("parse file failed. raw : " + raw);
        return;
      }
      instructionMap.put(pc, instruction);
    }

    Interpreter interpreter = new Interpreter();
    Thread thread = new Thread(2);
    KMethod method = new KMethod(1, "main", "()I", stacks, locals, instructionMap, null);
    Frame frame = new Frame(method, thread);
    thread.pushFrame(frame);

    // args
    for (int i = 0; i < argsCnt; i++) {
      frame.setInt(i, methodArgs.get(i));
    }

    interpreter.loop(thread);
  }
}
