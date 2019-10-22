package com.gxk.jvm.interpret;

import com.gxk.jvm.classloader.ClassLoader;
import com.gxk.jvm.classpath.Classpath;
import com.gxk.jvm.classpath.Entry;
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
import com.gxk.jvm.rtda.heap.KClass;
import com.gxk.jvm.rtda.heap.KMethod;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class InterpreterTest {

  @Test
  public void interpret() {
    Interpreter interpreter = new Interpreter();

    Map<Integer, Instruction> instructions = sum10Instructions();

    KMethod method = new KMethod(1, "sum10", "V()", 2, 3, instructions);

    interpreter.interpret(method);
  }

  @Test
  public void test_hello_main() {
    testMain("Hello");
  }

  @Test
  public void test_with_class() {
    KClass clazz = loadAndGetClazz("Loop1");
    KMethod method = clazz.getMethods().get(2);

    Thread thread = new Thread(1024);
    Frame frame = new Frame(method, thread);

    thread.pushFrame(frame);
    frame.localVars.setInt(0, 10);
    frame.localVars.setInt(1, 20);

    new Interpreter().loop(thread);
  }

  @Test
  public void test_loop2() {
    testMain("Loop2");
  }

  @Test
  public void test_loop100() {
    testMain("Loop100");
  }

  @Test
  public void test_method_with_args() {
    KClass clazz = loadAndGetClazz("Loop3");
    KMethod method = clazz.getMethods().get(2);

    Thread thread = new Thread(1024);
    Frame frame = new Frame(method, thread);

    thread.pushFrame(frame);
    frame.localVars.setInt(0, 10);
    frame.localVars.setInt(1, 20);

    new Interpreter().loop(thread);
  }

  @Test
  public void test_method_invoke() {
    testMain("Loop4");
  }

  @Test
  public void test_method_invoke_with_args() {
    testMain("Loop3");
  }

  @Test
  public void test_method_recur_invoke() {
    testMain("AddN");
  }

  @Test
  public void test_method_recur_invoke_with_args() {
    testMain("Fibonacci");
  }

  @Test
  public void test_method_invoke_with_two_int() {
    testMain("AddTwoInt");
  }

  @Test
  public void test_method_with_add_two_int() {
    KClass clazz = loadAndGetClazz("AddTwoInt");
    KMethod method = clazz.getMethods().get(2);

    Thread thread = new Thread(1024);
    Frame frame = new Frame(method, thread);

    thread.pushFrame(frame);
    frame.localVars.setInt(0, 10);
    frame.localVars.setInt(1, 20);

    new Interpreter().loop(thread);
  }

  @Test
  public void test_static_field() {
    testMain("TestStatic");
  }

  @Test
  public void test_object() {
    testMain("TestObject");
  }

  @Test
  public void test_object2() {
    testMain("TestObject2");
  }

  @Test
  public void test_object3() {
    testMain("TestObject3");
  }

  @Test
  public void test_object4() {
    testMain("TestObject4");
  }

  @Test
  public void test_array_0() {
    KMethod method = loadAndGetMainMethod("HelloWorld");
    new Interpreter().interpret(method, new String[]{"hello", "mini-jvm"});
  }

  private void testMain(String hello) {
    KMethod method = loadAndGetMainMethod(hello);
    new Interpreter().interpret(method);
  }

  private KMethod loadAndGetMainMethod(String clazzName) {
    String home = System.getenv("JAVA_HOME");
    Path jarPath = Paths.get(home, "jre", "lib", "rt.jar");
    Entry entry = Classpath.parse("example:" + jarPath.toFile().getAbsolutePath());

    ClassLoader loader = new ClassLoader("boot", entry);
    KClass clazz = loader.loadClass(clazzName);
    KMethod method = clazz.getMainMethod();
    return method;
  }

  private KClass loadAndGetClazz(String clazzName) {
    String home = System.getenv("JAVA_HOME");
    Path jarPath = Paths.get(home, "jre", "lib", "rt.jar");
    Entry entry = Classpath.parse("example:" + jarPath.toFile().getAbsolutePath());
    ClassLoader loader = new ClassLoader("boot", entry);
    KClass clazz = loader.loadClass(clazzName);
    return clazz;
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
}