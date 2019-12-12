package com.gxk.jvm;

import java.util.Objects;

/**
 * java -cp target/mini.jar com.gxk.Main xxxxxx
 */
class Args {

  private static final String MINUS_VERSION = "-version";
  private static final String MINUS_HELP= "-help";
  private static final String MINUS_VERBOSE= "-verbose";
  private static final String MINUS_TRACE= "-trace";

  private static final String MINUS_CP = "-cp";
  boolean version;
  boolean help;
  boolean verbose;
  boolean trace;

  String classpath = ".";
  String clazz;
  String[] args;

  static Args parseArgs(String... cliArgs) {
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
    while (!Objects.equals(MINUS_CP, cliArgs[idx]) && cliArgs[idx].startsWith("-")) {
      if (Objects.equals(MINUS_VERBOSE, cliArgs[idx])) {
        idx++;
        args.verbose = true;
      }

      if (Objects.equals(MINUS_TRACE, cliArgs[idx])) {
        idx++;
        args.trace = true;
      }
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
