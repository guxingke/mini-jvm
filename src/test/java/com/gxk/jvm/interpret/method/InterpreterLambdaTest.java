package com.gxk.jvm.interpret.method;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import org.junit.Test;

/**
 * lambda 相关
 */
public class InterpreterLambdaTest extends BaseInterpreterTest {

  @Test
  public void test_runnable() {
    testMain("TestLambda");
  }

  @Test
  public void test_consumer() {
    testMain("TestLambda2");
  }

  @Test
  public void test_supplier() {
    testMain("TestLambda3");
  }

  @Test
  public void test_supplier2() {
    testMain("TestLambda4");
  }

  @Test
  public void test_curry() {
    testMain("Curry2Args");
  }

  @Test
  public void test_curry3() {
    testMain("Curry3Args");
  }

  @Test
  public void test_closure() {
    testMain("TestClosure");
  }

  @Test
  public void test_closure2() {
    testMain("TestClosure2");
  }
}
