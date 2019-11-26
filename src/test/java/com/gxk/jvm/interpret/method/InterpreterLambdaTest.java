package com.gxk.jvm.interpret.method;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import org.junit.Ignore;
import org.junit.Test;

/**
 * lambda 相关
 */
public class InterpreterLambdaTest extends BaseInterpreterTest {

//  @Test
  @Ignore
  public void test_runnable() {
    testMain("TestLambda");
  }

  @Test
  public void test_consumer() {
    testMain("TestLambda2");
  }
}
