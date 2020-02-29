package com.gxk.jvm;

public class Main {

  public static void main(String[] args) {
    if (args.length == 0) {
      printHelp();
      return;
    }

    Args cmd = Args.parseArgs(args);
    if (cmd.version) {
      System.out.println("java version \"1.8.0\"");
      return;
    }

    if (cmd.help) {
      printHelp();
      return;
    }

    VirtualMachine vm = new VirtualMachine();
    vm.run(cmd);
  }

  private static void printHelp() {
    System.out.println(HELP_MESSAGE);
  }

  private static final String HELP_MESSAGE = "usage :\n"
      + "  ./bin/java [-options] class [args...] // e.g ./java -verbose:trace -cp example Hello\n"
      + "           (run class)\n"
      + "\n"
      + "   or\n"
      + "\n"
      + "  ./bin/java [-options] -jar jarfile [args...]\n"
      + "           (run jar)\n"
      + "\n"
      + "\n"
      + "options :\n"
      + "    -cp <dir or zip/jar file location>\n"
      + "\n"
      + "    -version            print version info and quit\n"
      + "\n"
      + "    -help               print help message\n"
      + "\n"
      + "    -verbose            print detail info  \n"
      + "    -verbose:debug      print debug message\n"
      + "    -verbose:call       print function call message\n"
      + "    -verbose:trace      print trace message\n"
      + "    -verbose:class      print class loaded message\n"
      + "\n"
      + "------\n"
      + "for simplify, other options must before -cp\n"
      + "e.g\n"
      + "./bin/java -cp example -verbose Hello => false\n"
      + "./bin/java -verbose -cp example Hello => true\n"
      + "./bin/java -verbose Hello => true\n"
      + "\n"
      + "more info, please notice https://github.com/guxingke/mini-jvm\n"
      + "\n";
}
