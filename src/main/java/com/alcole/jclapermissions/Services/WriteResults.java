package com.alcole.jclapermissions.Services;

import com.alcole.jclapermissions.Model.PermissionResult;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteResults {

  private static CSVPrinter csvPrinter;

  public static void write(String fileName, ResultList results) {
    // String fileName = dateTime + "_" + "results.csv";
    try {
      BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));
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
                  .withQuoteMode(QuoteMode.NONE));
      for (PermissionResult result : results) {
        csvPrinter.printRecord(result.toArray());
      }
      csvPrinter.flush();
    } catch (IOException e) {
      System.out.println("Exception: " + e.getMessage());
    }
  }
}
