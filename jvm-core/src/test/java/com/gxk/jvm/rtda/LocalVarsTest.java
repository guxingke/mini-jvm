package com.gxk.jvm.rtda;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocalVarsTest {

  @Test
  public void test_int() {
    LocalVars vars = new LocalVars(1);

    vars.setInt(0, Integer.MAX_VALUE);

    Integer ret = vars.getInt(0);

    assertEquals((int) ret, Integer.MAX_VALUE);
  }

  @Test
  public void test_float() {
    LocalVars vars = new LocalVars(1);
    float val = 1.11f;

    vars.setFloat(0, val);
    Float ret = vars.getFloat(0);
    assertEquals(ret, val, 0.0);
  }

  @Test
  public void test_Long() {
    LocalVars vars = new LocalVars(2);
    long val = Integer.MAX_VALUE + 1L;

    vars.setLong(0, val);
    Long ret = vars.getLong(0);

    assertEquals((long) ret, val);
  }

  @Test
  public void test_double() {
    LocalVars vars = new LocalVars(2);

    double val = 10.01d;
    vars.setDouble(0, val);

    Double ret = vars.getDouble(0);
    assertEquals(ret, val, 0.0d);
  }

  @Test
  public void test_ref() {
    LocalVars vars = new LocalVars(1);
    Long val = 1L;

    vars.setRef(0, val);
    Object ret = vars.getRef(0);

    assertEquals(ret, val);
  }
}