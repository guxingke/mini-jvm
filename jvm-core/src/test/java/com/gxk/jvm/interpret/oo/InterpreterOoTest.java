package com.gxk.jvm.interpret.oo;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import org.junit.Test;

public class InterpreterOoTest extends BaseInterpreterTest {

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
  public void test_passobject() {
    testMain("PassObject");
  }
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
  public void test_int4() {
    testMain("Int4Impl");
  }

}
