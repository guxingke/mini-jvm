package com.gxk.jvm;

import java.util.Objects;

/**
 * java -cp target/mini.jar com.gxk.Main xxxxxx
 */
public class Args {

  private static final String MINUS_VERSION = "-version";
  private static final String MINUS_HELP= "-help";
  private static final String MINUS_VERBOSE= "-verbose";
  private static final String MINUS_VERBOSE_TRACE= "-verbose:trace";
  private static final String MINUS_VERBOSE_CLASS = "-verbose:class";
  private static final String MINUS_VERBOSE_DEBUG= "-verbose:debug";

  private static final String MINUS_CP = "-cp";
  boolean version;
  boolean help;
  boolean verbose;
  boolean verboseTrace;
  boolean verboseClass;
  boolean verboseDebug;

  public String classpath = ".";
  public String clazz;
  public String[] args;

  public static Args parseArgs(String... cliArgs) {
    Args args = new Args();

    if (Objects.equals(MINUS_VERSION, cliArgs[0])) {
      args.version = true;
      return args;
    }

    if (Objects.equals(MINUS_HELP, cliArgs[0])) {
      args.help = true;
      return args;
    }

    int idx = 0;
    int tries = 0;
    while (!Objects.equals(MINUS_CP, cliArgs[idx]) && cliArgs[idx].startsWith("-")) {
      if (tries > 200) {
        System.out.println("parse args in loop. check input args.");
        System.exit(-1);
      }
      if (Objects.equals(MINUS_VERBOSE, cliArgs[idx])) {
        idx++;
        args.verbose = true;
      }

      if (Objects.equals(MINUS_VERBOSE_TRACE, cliArgs[idx])) {
        idx++;
        args.verboseTrace = true;
      }

      if (Objects.equals(MINUS_VERBOSE_CLASS, cliArgs[idx])) {
        idx++;
        args.verboseClass = true;
      }

      if (Objects.equals(MINUS_VERBOSE_DEBUG, cliArgs[idx])) {
        idx++;
        args.verboseDebug = true;
      }

      tries++;
    }

    if (MINUS_CP.equals(cliArgs[idx])) {
      idx++;
      args.classpath = cliArgs[idx++];
      args.clazz = cliArgs[idx++];

      args.args = new String[0];
      if (cliArgs.length > idx) {
        String[] programArgs = new String[cliArgs.length - idx];
        System.arraycopy(cliArgs, idx, programArgs, 0, programArgs.length);

        args.args = programArgs;
      }
      return args;
    }

    args.clazz = cliArgs[idx++];
    if (cliArgs.length > idx) {
      String[] programArgs = new String[cliArgs.length - idx];
      System.arraycopy(cliArgs, idx, programArgs, 0, programArgs.length);
      args.args = programArgs;
    }

    return args;
  }
}
