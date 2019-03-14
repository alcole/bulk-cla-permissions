package com.alcole.jclapermissions.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public final class WriteLog {

  private WriteLog() {
    // hidden
  }

  public static void appendLine(final String fileName, String line) {
    line = line + "\r\n";
    try {
      Files.write(Paths.get("out", fileName), line.getBytes(), StandardOpenOption.APPEND);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.out.println(e.toString());
    }
  }

  public static void setupFile(String fileName) {
    Path p = Paths.get("out", fileName);
    try {
      Files.createFile(p);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
