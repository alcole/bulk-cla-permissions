package com.alcole.jclapermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadIdentifiers {

    private static ReadIdentifiers instance = null;

    private static final String COMMA_DELIMITER = ",";

    private String getRecordFromLine(String line) {
        String value = "";
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                value = rowScanner.next().trim();
            }
        }
        return value;
    }

    private List<String> readCsv() {
        List<String> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("toCheck.csv"))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static List<String> getIdentifiers() {
        if (instance == null) instance = new ReadIdentifiers();
        return instance.readCsv();
    }
}
