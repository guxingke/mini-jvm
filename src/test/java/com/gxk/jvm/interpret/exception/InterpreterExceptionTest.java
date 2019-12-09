package com.gxk.jvm.interpret.exception;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import org.junit.Ignore;
import org.junit.Test;

public class InterpreterExceptionTest extends BaseInterpreterTest {

  @Test
  public void test_simple() {
    testMain("TestException");
  }
}
