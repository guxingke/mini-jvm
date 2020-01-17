package com.gxk.jvm.interpret.reflect;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import org.junit.Test;

public class ReflectionInterpreterTest extends BaseInterpreterTest {

  @Test
  public void test_class() {
    testMain("TestClass");
  }

  @Test
  public void test_class2() {
    testMain("TestClass2");
  }

  @Test
  public void test_class3() {
    testMain("TestClass3");
  }
}
