package com.gxk.jvm.interpret.misc;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import com.gxk.jvm.interpret.Interpreter;
import com.gxk.jvm.rtda.heap.Method;
import org.junit.Test;

public class InterpreterMiscTest extends BaseInterpreterTest {

  @Test
  public void test_hello_main() {
    testMain("Hello");
  }

  @Test
  public void test_hello_sb() {
    testMain("Hello2");
  }

  @Test
  public void test_hello_sb2() {
    testMain("Hello3");
  }

  @Test
  public void test_hello_long() {
    testMain("Hello4");
  }

  @Test
  public void test_static_field() {
    testMain("TestStatic");
  }

  @Test
  public void test_array_0() {
    Method method = loadAndGetMainMethod("HelloWorld");
    Interpreter.runMain(method, new String[]{"hello", "mini-jvm"});
  }

  @Test
  public void test_array_1() {
    Method method = loadAndGetMainMethod("HelloWorld");
    Interpreter.runMain(method, new String[0]);
  }

  @Test
  public void test_switch_0() {
    testMain("SwitchTest0");
  }

  @Test
  public void test_switch_1() {
    testMain("SwitchTest1");
  }
}
