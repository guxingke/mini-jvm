package com.gxk.jvm.interpret.primitive;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import org.junit.Test;

public class InterpreterPrimitiveTest extends BaseInterpreterTest {

  @Test
  public void test_int2() {
    testMain("Int2Impl");
  }

  @Test
  public void test_int32() {
    testMain("Int3Impl2");
  }

  @Test
  public void test_int42() {
    testMain("Int4Impl2");
  }
  @Test
  public void test_hello_bool() {
    testMain("TrueFalse");
  }
}
