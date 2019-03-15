package com.alcole.jclapermissions.Services;

import com.alcole.jclapermissions.Model.PermissionResult;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * service class to write the permission results to file.
 */
public class WriteResults {

  /**
   * method to write out the permissions to csv.
   * @param fileName the name of the csv file including extension
   * @param results a results list of the permissions to write
   */
  public static void write(final String fileName, final ResultList results) {
    CSVPrinter csvPrinter;
    try {
      BufferedWriter writer = Files.newBufferedWriter(Paths.get("out", fileName));
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
