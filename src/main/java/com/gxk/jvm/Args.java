package com.gxk.jvm;

/**
 * java -cp target/mini.jar com.gxk.Main xxxxxx
 */
public class Args {

  public boolean version;
  public String classpath;
  public String clazz;
  public String[] args;


  public static Args parseArgs(String... cliArgs) {
    Args args = new Args();

    if (cliArgs[0].equals("-version")) {
      args.version = true;
      return args;
    }

    if (cliArgs[0].equals("-cp")) {
      args.classpath = cliArgs[1];
      args.clazz = cliArgs[2];

      if (cliArgs.length > 3) {
        String[] programArgs = new String[cliArgs.length - 3];
        System.arraycopy(cliArgs, 3, programArgs, 0, programArgs.length);

        args.args = programArgs;
      }
      return args;
    }

    args.clazz = cliArgs[0];
    if (cliArgs.length > 1) {
      String[] programArgs = new String[cliArgs.length - 1];
      System.arraycopy(cliArgs, 1, programArgs, 0, programArgs.length);
      args.args = programArgs;
    }

    return args;
  }
}
