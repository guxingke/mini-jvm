package com.gxk.jvm;

/**
 * java -cp target/mini.jar com.gxk.Main xxxxxx
 */
class Args {

  private static final String MINUS_VERSION = "-version";
  private static final String MINUS_CP = "-cp";
  private static final int ARGS_LIMIT = 3;
  boolean version;
  String classpath;
  String clazz;
  String[] args;


  static Args parseArgs(String... cliArgs) {
    Args args = new Args();

    if (MINUS_VERSION.equals(cliArgs[0])) {
      args.version = true;
      return args;
    }

    if (MINUS_CP.equals(cliArgs[0])) {
      args.classpath = cliArgs[1];
      args.clazz = cliArgs[2];
      args.args = new String[0];

      if (cliArgs.length > ARGS_LIMIT) {
        String[] programArgs = new String[cliArgs.length - 3];
        System.arraycopy(cliArgs, 3, programArgs, 0, programArgs.length);

        args.args = programArgs;
      }
      return args;
    }

    args.clazz = cliArgs[0];
    args.args = new String[0];
    if (cliArgs.length > 1) {
      String[] programArgs = new String[cliArgs.length - 1];
      System.arraycopy(cliArgs, 1, programArgs, 0, programArgs.length);
      args.args = programArgs;
    }

    return args;
  }
}
