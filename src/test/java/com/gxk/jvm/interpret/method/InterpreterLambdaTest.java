package com.gxk.jvm.interpret.method;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import org.junit.Test;

/**
 * lambda 相关
 */
public class InterpreterLambdaTest extends BaseInterpreterTest {

//  @Test
  public void test_runnable() {
    testMain("TestLambda");
  }
}
