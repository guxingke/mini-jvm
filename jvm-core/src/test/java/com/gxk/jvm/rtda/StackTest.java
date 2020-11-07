package com.gxk.jvm.rtda;

import com.gxk.jvm.rtda.heap.Method;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {

  @Test
  public void test() {
    Stack<Frame> stack = new Stack<>(1024);
    Method method = new Method(1, "x", "x", 1, 1, null, null, null);
    stack.push(new Frame(method));

    assertEquals(1, stack.size());
    assertEquals(1024, stack.maxSize);
  }
}