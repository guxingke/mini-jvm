package com.gxk.jvm.rtda;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {

  @Test
  public void test() {
    Stack<Frame> stack = new Stack<>(1024);
    stack.push(new Frame(1, 1, null, null));

    assertEquals(1, stack.size());
    assertEquals(1024, stack.maxSize);
  }
}