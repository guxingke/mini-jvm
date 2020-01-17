package com.gxk.jvm.util;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

public class UtilsTest {

  @Test
  public void parseMethodDescriptor() {
    List<String> rets = Utils.parseMethodDescriptor("()V");
    assertEquals(0, rets.size(), 0);
  }

  @Test
  public void parseMethodDescriptor_1() {
    List<String> rets = Utils.parseMethodDescriptor("(II[CI)V");
    assertEquals(4, rets.size(), 0);
  }

  @Test
  public void parseMethodDescriptor_2() {
    List<String> rets = Utils.parseMethodDescriptor("(IILjava/lang/String;I)V");
    assertEquals(4, rets.size(), 0);
  }

  @Test
  public void parseMethodDescriptor_3() {
    List<String> rets = Utils.parseMethodDescriptor("(II[Ljava/lang/String;I)V");
    assertEquals(4, rets.size(), 0);
  }

  @Test
  public void parseMethodDescriptor_4() {
    List<String> rets = Utils.parseMethodDescriptor("([[[II[Ljava/lang/String;I)V");
    assertEquals(4, rets.size(), 0);
  }
}