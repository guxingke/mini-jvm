package com.gxk.jvm.interpret;

import com.gxk.jvm.classfile.ClassFile;
import com.gxk.jvm.classfile.ClassReader;
import com.gxk.jvm.classfile.CodeAttribute;
import com.gxk.jvm.classfile.CodeFromByte;
import com.gxk.jvm.classfile.Method;
import com.gxk.jvm.classfile.MethodInfo;
import com.gxk.jvm.classfile.attribute.Code;
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
import com.gxk.jvm.rtda.Frame;
import com.gxk.jvm.rtda.Thread;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class InterpreterTest {

  @Test
  public void interpret() {
    Interpreter interpreter = new Interpreter();

    Map<Integer, Instruction> instructions = sum10Instructions();
    CodeFromByte code = new CodeFromByte(instructions);
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
    map.put(7, new IfIcmpGtInst((short) 13));
    map.put(10, new Iload1Inst());
    map.put(11, new Iload2Inst());
    map.put(12, new IaddInst());
    map.put(13, new Istore1Inst());
    map.put(14, new IIncInst(2, 1));
    map.put(17, new Goto1Inst((short) -13));
    map.put(20, new Iload1Inst());
    map.put(21, new IreturnInst());
    return map;
  }

  @Test
  public void test_hello_main() throws Exception {
    ClassFile cf = ClassReader.read(Paths.get("example/Hello.class"));
    Method main = cf.methods.methods[1];

    com.gxk.jvm.classfile.attribute.Code attribute = (com.gxk.jvm.classfile.attribute.Code) main.attributes.attributes[0];

    MethodInfo method = map(attribute);

    new Interpreter().interpret(method);
  }

  @Test
  public void test_with_class() throws Exception {
    ClassFile cf = ClassReader.read(Paths.get("example/Loop1.class"));
    Method main = cf.methods.methods[2];

    com.gxk.jvm.classfile.attribute.Code attribute = (com.gxk.jvm.classfile.attribute.Code) main.attributes.attributes[0];

    MethodInfo method = map(attribute);

    new Interpreter().interpret(method);
  }

  @Test
  public void test_loop2() throws Exception {
    ClassFile cf = ClassReader.read(Paths.get("example/Loop2.class"));
    Method main = cf.getMainMethod();
    com.gxk.jvm.classfile.attribute.Code attribute = (com.gxk.jvm.classfile.attribute.Code) main.attributes.attributes[0];
    MethodInfo method = map(attribute);
    new Interpreter().interpret(method);
  }

  @Test
  public void test_loop100() throws Exception {
    ClassFile cf = ClassReader.read(Paths.get("example/Loop100.class"));
    Method main = cf.getMainMethod();
    com.gxk.jvm.classfile.attribute.Code attribute = (com.gxk.jvm.classfile.attribute.Code) main.attributes.attributes[0];
    MethodInfo method = map(attribute);
    new Interpreter().interpret(method);
  }

  @Test
  public void test_method_with_args() throws Exception {
    ClassFile cf = ClassReader.read(Paths.get("example/Loop3.class"));
    Method main = cf.methods.methods[2];

    com.gxk.jvm.classfile.attribute.Code attribute = (com.gxk.jvm.classfile.attribute.Code) main.attributes.attributes[0];
    MethodInfo method = map(attribute);

    Thread thread = new Thread(1024);
    Frame frame = new Frame(method.code.maxLocals, method.code.maxStacks, method.code.code, thread);

    thread.pushFrame(frame);
    frame.localVars.setInt(0, 1000);

    new Interpreter().loop(thread);
  }

  @Test
  public void test_method_invoke() throws Exception {
    ClassFile cf = ClassReader.read(Paths.get("example/Loop4.class"));
    Method main = cf.getMainMethod();

    com.gxk.jvm.classfile.attribute.Code attribute = (com.gxk.jvm.classfile.attribute.Code) main.attributes.attributes[0];
    MethodInfo method = map(attribute);

    Thread thread = new Thread(1024);
    Frame frame = new Frame(method.code.maxLocals, method.code.maxStacks, method.code.code, thread);

    thread.pushFrame(frame);

    new Interpreter().loop(thread);
  }

  @Test
  public void test_method_with_add_two_int() throws Exception {
    ClassFile cf = ClassReader.read(Paths.get("example/AddTwoInt.class"));
    Method main = cf.methods.methods[2];

    com.gxk.jvm.classfile.attribute.Code attribute = (com.gxk.jvm.classfile.attribute.Code) main.attributes.attributes[0];
    MethodInfo method = map(attribute);

    Thread thread = new Thread(1024);
    Frame frame = new Frame(method.code.maxLocals, method.code.maxStacks, method.code.code, thread);

    thread.pushFrame(frame);
    frame.localVars.setInt(0, 10);
    frame.localVars.setInt(1, 20);

    new Interpreter().loop(thread);
  }

  private MethodInfo map(Code attribute) {
    int maxStacks = attribute.getMaxStacks();
    int maxLocals = attribute.getMaxLocals();
    Instruction[] instructions = attribute.getInstructions();

    Map<Integer, Instruction> map = new HashMap<>();
    int pc = 0;
    for (Instruction instruction : instructions) {
      map.put(pc, instruction);
      pc += instruction.offset();
    }
    CodeAttribute codeAttribute = new CodeAttribute(new CodeFromByte(map), maxLocals, maxStacks);
    return new MethodInfo(codeAttribute);
  }
}