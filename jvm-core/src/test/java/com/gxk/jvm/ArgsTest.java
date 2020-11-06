package com.gxk.jvm;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArgsTest {

  @Test
  public void test_normal() {
    Args args = Args.parseArgs("-cp", "target/test.jar", "Main", "help");

    assertTrue(!args.version);

    assertEquals("target/test.jar", args.classpath);
    assertEquals("Main", args.clazz);

    assertEquals(1, args.args.length);
    assertEquals("help", args.args[0]);
  }

  @Test
  public void test_without_program_args() {
    Args args = Args.parseArgs("-cp", "target/test.jar", "Main");

    assertTrue(!args.version);

    assertEquals("target/test.jar", args.classpath);
    assertEquals("Main", args.clazz);

    assertEquals(0, args.args.length);
  }

  @Test
  public void test_help() {
    Args args = Args.parseArgs("-help");
    assertTrue(args.help);
  }

  @Test
  public void test_verbose_cp() {
    Args args = Args.parseArgs("-verbose", "-cp", "example", "Main");
    assertTrue(args.verbose);
    assertEquals("example", args.classpath);
    assertEquals("Main", args.clazz);
  }

  @Test
  public void test_verbose_cp2() {
    Args args = Args.parseArgs("-cp", "example", "-verbose", "Main");
    assertTrue(args.verbose);
  }

  @Test
  public void test_version() {
    Args args = Args.parseArgs("-version");
    assertTrue(args.version);
  }
}