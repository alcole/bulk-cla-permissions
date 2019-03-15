package com.alcole.jclapermissions.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * class to manage writing details to a log file.
 */
public final class WriteLog {

  private static String fileName;

  private WriteLog() {
    // hidden
  }

  /**
   * adds lines to the logfile.
   * @param line line to add to the logfile.
   */
  public static void appendLine(final String line) {
    try {
      Files.write(Paths.get("out", fileName), (line + "\r\n").getBytes(), StandardOpenOption.APPEND);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.out.println(e.toString());
    }
  }

  /**
   * creates the logfile and stores filename.
   * @param file the name of the file to set up
   */
  public static void setupFile(final String file) {
    fileName = file;
    Path p = Paths.get("out", fileName);
    try {
      Files.createFile(p);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
