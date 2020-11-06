package com.gxk.jvm;

import com.gxk.jvm.util.EnvHolder;
import com.gxk.jvm.util.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * java -cp target/mini.jar com.gxk.Main xxxxxx
 */
public class Args {

  private static final String MINUS_VERSION = "-version";
  private static final String MINUS_HELP = "-help";

  private static final String MINUS_COLOR_RED = "-Xcolor:red";
  private static final String MINUS_COLOR_GREEN = "-Xcolor:green";
  private static final String MINUS_COLOR_YELLOW = "-Xcolor:yellow";

  boolean version;
  boolean help;
  boolean verbose;
  boolean verboseTrace;
  boolean verboseCall;
  boolean verboseClass;
  boolean verboseDebug;

  public String classpath = ".";
  public String clazz;
  public String[] args = new String[0];

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

    // 解析参数
    int programArgIdx = 0;
    boolean isJar = false;
    String jar = null;
    for (int i = 0; i < cliArgs.length; i++) {
      if (Objects.equals(MINUS_COLOR_RED, cliArgs[i])) {
        Logger.fg = Logger.ANSI_RED;
        continue;
      }
      if (Objects.equals(MINUS_COLOR_GREEN, cliArgs[i])) {
        Logger.fg = Logger.ANSI_GREEN;
        continue;
      }

      if (Objects.equals(MINUS_COLOR_YELLOW, cliArgs[i])) {
        Logger.fg = Logger.ANSI_YELLOW;
        continue;
      }

      if (cliArgs[i].startsWith("-verbose:")) {
        final String[] split = cliArgs[i].split(":");
        switch (split[1]) {
          case "call":
            args.verboseCall = true;
            continue;
          case "trace":
            args.verboseTrace = true;
            continue;
          case "class":
            args.verboseClass = true;
            continue;
          case "debug":
            args.verboseDebug = true;
            continue;
          default:
            continue;
        }
      }

      if (cliArgs[i].equals("-verbose")) {
        args.verbose = true;
        continue;
      }

      if (cliArgs[i].equals("-cp")) {
        args.classpath = cliArgs[i + 1];
        i++;
        continue;
      }

      if (cliArgs[i].equals("-jar")) {
        isJar = true;
        jar = cliArgs[i + 1];
        args.classpath = args.classpath + ":" + jar;
        i++;
        programArgIdx = i + 1;
        break;
      }

      if (cliArgs[i].startsWith("-")) {
        continue;
      }

      if (args.clazz == null && !isJar) {
        args.clazz = cliArgs[i].replace('.', '/');
        programArgIdx = i + 1;
      }
    }

    if (isJar) {
      args.clazz = parseMainClass(jar);
    }

    if (programArgIdx < cliArgs.length) {
      // -cp . hello a1 a2
      args.args = new String[cliArgs.length - programArgIdx];
      System.arraycopy(cliArgs, programArgIdx, args.args, 0, args.args.length);
    }

    return args;
  }

  private static String parseMainClass(String mainJar) {
    String userDir = System.getProperty("user.dir");
    String path = userDir + EnvHolder.FILE_SEPARATOR + mainJar;
    try (ZipFile file = new ZipFile(path)) {
      ZipEntry entry = file.getEntry("META-INF/MANIFEST.MF");

      try (InputStream is = file.getInputStream(entry)) {
        String line;
        while ((line = readLine(is)) != null) {
          if (line.startsWith("Main-Class: ")) {
            return line.substring(12);
          }
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
    throw new IllegalArgumentException("Not found main class");
  }

  private static String readLine(InputStream is) throws IOException {
    StringBuilder line = new StringBuilder();
    int b = is.read();
    if (b < 0) {
      return null;
    }
    while (b > 0) {
      char c = (char) b;
      if (c == '\r' || c == '\n') {
        break;
      }
      if (c == '.') {
        c = '/';
      }
      line.append(c);
      b = is.read();
    }
    return line.toString();
  }
}
