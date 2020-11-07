package com.gxk.jvm.interpret.method;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import org.junit.Test;

/**
 * 方法相关单测
 */
public class InterpreterMethodInfoTest extends BaseInterpreterTest {

//  @Test
//  public void test_with_class() {
//    KClass clazz = loadAndGetClazz("Loop1");
//    KMethod method = clazz.methods.get(2);
//
//    final Thread thread = new Thread(1024);
//    MetaSpace.main = thread;
//    Frame frame = new Frame(method);
//
//    thread.pushFrame(frame);
//    frame.setInt(0, 10);
//    frame.setInt(1, 20);
//
//    new Interpreter().loop(thread);
//  }

  @Test
  public void test_loop2() {
    testMain("Loop2");
  }

  @Test
  public void test_loop100() {
    testMain("Loop100");
  }

//  @Test
//  public void test_method_with_args() {
//    KClass clazz = loadAndGetClazz("Loop3");
//    KMethod method = clazz.methods.get(2);
//
//    final Thread thread = new Thread(1024);
//    MetaSpace.main = thread;
//    Frame frame = new Frame(method);
//
//    thread.pushFrame(frame);
//    frame.setInt(0, 10);
//    frame.setInt(1, 20);
//
//    new Interpreter().loop(thread);
//  }

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

//  @Test
//  public void test_method_with_add_two_int() {
//    KClass clazz = loadAndGetClazz("AddTwoInt");
//    KMethod method = clazz.methods.get(2);
//
//    Thread thread = new Thread(1024);
//    MetaSpace.main = thread;
//    Frame frame = new Frame(method);
//
//    thread.pushFrame(frame);
//    frame.setInt(0, 10);
//    frame.setInt(1, 20);
//
//    new Interpreter().loop(thread);
//  }
}
