package com.alcole.jclapermissions.Services;

import com.alcole.jclapermissions.Model.PermissionResult;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteResults {

  private static CSVPrinter csvPrinter;

  public static void write(String fileName, ResultList results) {
    try {
      BufferedWriter writer = Files.newBufferedWriter(Paths.get("out",fileName));
      csvPrinter =
          new CSVPrinter(
              writer,
              CSVFormat.DEFAULT
                  .withHeader(
                      "Identifier",
                      "Title",
                      "Format",
                      "Publisher",
                      "Publisher Country",
                      "Photocopying",
                      "Scanning",
                      "Digital")
                  .withEscape('\\')
                  .withQuote('"'));
      for (PermissionResult result : results) {
        csvPrinter.printRecord(result.toArray());
      }
      csvPrinter.flush();
    } catch (IOException e) {
      System.out.println("Exception: " + e.getMessage());
    }
  }
}
