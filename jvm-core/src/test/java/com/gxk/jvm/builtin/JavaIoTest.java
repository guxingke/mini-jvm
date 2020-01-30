package com.gxk.jvm.builtin;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import com.gxk.jvm.util.EnvHolder;
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

  @Test
  public void test_file_exists() {
    testMain("FileTest");
  }

  @Test
  public void test_fis() {
    testMain("FileTest2");
  }

  @Test
  public void test_data_input_stream() {
    testMain("FileTest3");
  }

  @Test
  public void test_class_file() {
    testMain("FileTest4");
  }

  @Test
  public void test_utf8() {
    testMain("FileTest5");
  }

  @Test
  public void test_bit() {
    testMain("BitOpTest");
  }
}
