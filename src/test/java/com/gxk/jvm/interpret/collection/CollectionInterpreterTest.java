package com.gxk.jvm.interpret.collection;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import org.junit.Test;

public class CollectionInterpreterTest extends BaseInterpreterTest {

  @Test
  public void test_list() {
    testMain("TestList");
  }

  @Test
  public void test_list2() {
    testMain("TestList2");
  }

  @Test
  public void test_array_list() {
    testMain("TestArrayList");
  }

  @Test
  public void test_map() {
    testMain("TestMap");
  }

  @Test
  public void test_deque() {
    testMain("TestStack");
  }

  @Test
  public void test_stack() {
    testMain("TestStack2");
  }
}
