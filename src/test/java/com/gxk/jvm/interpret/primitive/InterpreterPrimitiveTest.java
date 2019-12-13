package com.gxk.jvm.interpret.primitive;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import org.junit.Test;

public class InterpreterPrimitiveTest extends BaseInterpreterTest {

  @Test
  public void test_hello_bool() {
    testMain("TrueFalse");
  }
}
