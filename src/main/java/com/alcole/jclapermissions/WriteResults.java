package com.alcole.jclapermissions;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteResults {

    private static WriteResults instance = null;
    private static CSVPrinter csvPrinter;

    private WriteResults(String dateTime) {
        String fileName = dateTime + "_" + "results.csv";
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Identifier"));
        }
        catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static CSVPrinter getCsvPrinter(String dateTime) {
        if (instance == null) instance = new WriteResults(dateTime);
        return instance.csvPrinter;
    }


    public static void writeLine(String result) {
        try {
            csvPrinter.printRecord(result);
        }
        catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static void flush() {
        try {
            csvPrinter.flush();
        }
        catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

}
