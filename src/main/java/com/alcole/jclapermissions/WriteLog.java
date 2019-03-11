package com.alcole.jclapermissions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriteLog {

  public static void appendLine(String fileName, String line) {
    line = line + "\r\n";
    try {
      Files.write(Paths.get(fileName), line.getBytes(), StandardOpenOption.APPEND);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void setupFile(String fileName) {
    Path p = Paths.get(fileName);
    try {
      Files.createFile(p);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
