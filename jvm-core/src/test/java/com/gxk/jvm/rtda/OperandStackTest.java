package com.gxk.jvm.rtda;

import com.gxk.jvm.rtda.heap.KObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperandStackTest {

  @Test
  public void test_int() {
    OperandStack stack = new OperandStack(1);

    Integer val = Integer.MAX_VALUE;

    stack.pushInt(val);
    Integer ret = stack.popInt();

    assertEquals(ret, val);
  }

  @Test
  public void test_float() {
    OperandStack stack = new OperandStack(1);

    float val = 1.1f;

    stack.pushFloat(val);
    Float ret = stack.popFloat();

    assertEquals(ret, val, 0.0f);
  }

  @Test
  public void test_long() {
    OperandStack stack = new OperandStack(2);

    long val = Long.MAX_VALUE;

    stack.pushLong(val);
    Long ret = stack.popLong();

    assertEquals(ret, val, 0L);
  }

  @Test
  public void test_double() {
    OperandStack stack = new OperandStack(2);

    double val = 1.1d;

    stack.pushDouble(val);
    Double ret = stack.popDouble();

    assertEquals(ret, val, 0.0d);
  }

  @Test
  public void test_ref() {
    OperandStack stack = new OperandStack(1);

    KObject val = new KObject(null);

    stack.pushRef(val);
    Object ret = stack.popRef();

    assertEquals(ret, val);
  }
}