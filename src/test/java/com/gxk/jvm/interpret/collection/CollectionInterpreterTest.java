package com.gxk.jvm.interpret.collection;

import com.gxk.jvm.interpret.BaseInterpreterTest;
import com.gxk.jvm.util.EnvHolder;
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
  public void test_map() {
    EnvHolder.trace = true;
    testMain("TestMap");
  }
}
