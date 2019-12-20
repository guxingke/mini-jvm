package com.gxk.jvm.tools;

import com.gxk.jvm.Args;
import com.gxk.jvm.VirtualMachine;
import com.gxk.jvm.util.DebugContextHolder;
import com.gxk.jvm.util.EnvHolder;

/**
 * like jdk jdb, the java debugger.
 *
 * simply impl.
 *
 * java -cp target/mini-jvm.jar com.gxk.jvm.tools.Jdb -cp example Hello
 */
public class Jdb {

  public static void main(String[] args) {
    // parse args
    if (args.length == 0) {
      printUsage();
      return;
    }

    Args cmd = Args.parseArgs(args);

    EnvHolder.debug = true;
    DebugContextHolder.mainClass = cmd.clazz;
    VirtualMachine vm = new VirtualMachine();
    vm.run(cmd);
  }

  private static void printUsage() {
    System.out.println("e.g: java -cp target/mini-jvm.jar com.gxk.jvm.tools.Jdb -cp example Hello");
  }
}
