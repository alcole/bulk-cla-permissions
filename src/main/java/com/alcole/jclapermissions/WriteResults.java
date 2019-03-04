package com.alcole.jclapermissions;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WriteResults {

    //private static WriteResults instance = null;
    private static CSVPrinter csvPrinter;

    public static void write(String dateTime, List<PermissionResult> results) {
        //change to send file name
        String fileName = dateTime + "_" + "results.csv";
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Identifier","Title",  "Format", "Publisher", "Publisher Country", "other").withEscape('\\').withQuoteMode(QuoteMode.NONE));
            for (PermissionResult result : results) {
                csvPrinter.printRecord(result.toArray());
                //csvPrinter.printRecord(result.toString());
            }
            csvPrinter.flush();
        }
        catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

}
