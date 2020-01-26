package com.gxk.jvm.builtin;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import org.junit.Test;

public class JavaIoTest extends BaseInterpreterTest {

  @Test
  public void test_out() {
    testMain("FdTest");
  }

  @Test
  public void test_out0() {
    testMain("FdTest2");
  }

  @Test
  public void test_err() {
    testMain("FdTest3");
  }

  @Test
  public void test_ps() {
    testMain("PrintStreamTest");
  }

  @Test
  public void test_out_err() {
    testMain("SystemOutTest");
  }
}
