package com.gxk.jvm;

/**
 * java -cp target/mini.jar com.gxk.Main xxxxxx
 */
class Args {

  private static final String MINUS_VERSION = "-version";
  private static final String MINUS_HELP= "-help";
  private static final String MINUS_VERBOSE= "-verbose";
  private static final String MINUS_CP = "-cp";
  boolean version;
  boolean help;
  boolean verbose;

  String classpath;
  String clazz;
  String[] args;

  static Args parseArgs(String... cliArgs) {
    Args args = new Args();

    if (MINUS_VERSION.equals(cliArgs[0])) {
      args.version = true;
      return args;
    }

    if (MINUS_HELP.equals(cliArgs[0])) {
      args.help = true;
      return args;
    }

    int idx = 0;
    if (MINUS_VERBOSE.equals(cliArgs[idx])) {
      idx++;
      args.verbose= true;
    }

    if (MINUS_CP.equals(cliArgs[idx])) {
      idx++;
      args.classpath = cliArgs[idx++];
      args.clazz = cliArgs[idx++];

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
