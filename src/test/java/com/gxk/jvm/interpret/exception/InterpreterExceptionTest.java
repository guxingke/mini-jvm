package com.gxk.jvm.interpret.exception;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import org.junit.Test;

public class InterpreterExceptionTest extends BaseInterpreterTest {

  @Test
  public void test_simple() {
    testMain("TestException");
  }

  @Test
  public void test_exception_finally() {
    testMain("TestException2");
  }

  @Test
  public void test_exception_chain() {
    testMain("TestException3");
  }

  @Test(expected = RuntimeException.class)
  public void test_exception_chain2() {
    testMain("TestException4");
  }
}
