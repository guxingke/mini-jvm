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
  private static final String MINUS_VERBOSE = "-verbose";
  private static final String MINUS_VERBOSE_TRACE = "-verbose:trace";
  private static final String MINUS_VERBOSE_CALL = "-verbose:call";
  private static final String MINUS_VERBOSE_CLASS = "-verbose:class";
  private static final String MINUS_VERBOSE_DEBUG = "-verbose:debug";

  private static final String MINUS_COLOR_RED = "-Xcolor:red";
  private static final String MINUS_COLOR_GREEN = "-Xcolor:green";
  private static final String MINUS_COLOR_YELLOW = "-Xcolor:yellow";

  private static final String MINUS_CP = "-cp";
  private static final String MINUS_JAR = "-jar";

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

    if (cliArgs.length == 1) {
      args.clazz = cliArgs[0];
      return args;
    }

    int pi = 0;
    for (int i = 0; i < cliArgs.length; i++) {
      final String tmp = cliArgs[i];
      if (tmp.equals(MINUS_CP)) {
        final String cp = cliArgs[++i];
        if (args.classpath.equals(".")) {
          args.classpath = cp;
        } else {
          args.classpath = args.classpath.concat(":").concat(cp);
        }
        args.clazz = cliArgs[++i];
        pi = ++i;
        break;
      }
      if (tmp.equals(MINUS_JAR)) {
        final String jar = cliArgs[++i];
        args.classpath = args.classpath.concat(":").concat(jar);
        args.clazz = parseMainClass(jar);
        pi = ++i;
        break;
      }

      if (!tmp.startsWith("-")) {
        args.clazz = tmp;
        pi = ++i;
        break;
      }

      if (Objects.equals(MINUS_VERBOSE, tmp)) {
        args.verbose = true;
        continue;
      }

      if (Objects.equals(MINUS_VERBOSE_TRACE, tmp)) {
        args.verboseTrace = true;
        continue;
      }

      if (Objects.equals(MINUS_VERBOSE_CALL, tmp)) {
        args.verboseCall = true;
        continue;
      }

      if (Objects.equals(MINUS_VERBOSE_CLASS, tmp)) {
        args.verboseClass = true;
        continue;
      }

      if (Objects.equals(MINUS_VERBOSE_DEBUG, tmp)) {
        args.verboseDebug = true;
        continue;
      }

      if (Objects.equals(MINUS_COLOR_RED, tmp)) {
        Logger.fg = Logger.ANSI_RED;
        Logger.trace("testtest");
        continue;
      }

      if (Objects.equals(MINUS_COLOR_GREEN, tmp)) {
        Logger.PREFIX = "G";
        Logger.fg = Logger.ANSI_GREEN;
        continue;
      }

      if (Objects.equals(MINUS_COLOR_YELLOW, tmp)) {
        Logger.PREFIX = "Y";
        Logger.fg = Logger.ANSI_YELLOW;
        continue;
      }
    }

    if (cliArgs.length > pi) {
      String[] programArgs = new String[cliArgs.length - pi];
      System.arraycopy(cliArgs, pi, programArgs, 0, programArgs.length);
      args.args = programArgs;
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
