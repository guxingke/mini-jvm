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
}
