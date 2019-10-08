package com.gxk.jvm.interpret;

import com.gxk.jvm.classfile.Code;
import com.gxk.jvm.classfile.CodeAttribute;
import com.gxk.jvm.classfile.MethodInfo;
import com.gxk.jvm.instruction.BiPushInst;
import com.gxk.jvm.instruction.Goto1Inst;
import com.gxk.jvm.instruction.IIncInst;
import com.gxk.jvm.instruction.IaddInst;
import com.gxk.jvm.instruction.Iconst0Inst;
import com.gxk.jvm.instruction.Iconst1Inst;
import com.gxk.jvm.instruction.IfIcmpGtInst;
import com.gxk.jvm.instruction.Iload1Inst;
import com.gxk.jvm.instruction.Iload2Inst;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.instruction.IreturnInst;
import com.gxk.jvm.instruction.Istore1Inst;
import com.gxk.jvm.instruction.Istore2Inst;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class InterpreterTest {

  @Test
  public void interpret() {
    Interpreter interpreter = new Interpreter();

    Map<Integer, Instruction> instructions = sum10Instructions();
    Code code = new Code(instructions);

    CodeAttribute codeAttr = new CodeAttribute(code, 3, 2);
    MethodInfo method = new MethodInfo(codeAttr);

    interpreter.interpret(method);
  }

  private Map<Integer, Instruction> sum10Instructions() {
    Map<Integer, Instruction> map = new HashMap<>();
    map.put(0, new Iconst0Inst());
    map.put(1, new Istore1Inst());
    map.put(2, new Iconst1Inst());
    map.put(3, new Istore2Inst());
    map.put(4, new Iload2Inst());
    map.put(5, new BiPushInst((byte) 10));
    map.put(7, new IfIcmpGtInst((short) 20));
    map.put(10, new Iload1Inst());
    map.put(11, new Iload2Inst());
    map.put(12, new IaddInst());
    map.put(13, new Istore1Inst());
    map.put(14, new IIncInst(2, 1));
    map.put(17, new Goto1Inst(4));
    map.put(20, new Iload1Inst());
    map.put(21, new IreturnInst());
    return map;
  }
}