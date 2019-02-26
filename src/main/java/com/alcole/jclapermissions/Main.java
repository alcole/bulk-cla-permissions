package com.alcole.jclapermissions;

import org.apache.commons.csv.CSVPrinter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.alcole.bibliotools.IsnLib.getType;
import static com.alcole.bibliotools.IsnLib.issnFromEan13;

public class Main {


    private static Date date = Calendar.getInstance().getTime();
    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    private static String strDate = dateFormat.format(date);

    private static List<String> identifiers = new ArrayList<>();
    private static List<List<String>> results = new ArrayList<List<String>>();


    public static void main(String args[]) {
        String key = Key.getKey();
        String licence = SetLicence.getLicence(); // check if licence is valid

        identifiers = readIdentifiers.getIdentifiers();
        System.out.println(key + " : " + licence);
        System.out.println(strDate);
        CSVPrinter csvPrinter = WriteResults.getCsvPrinter(strDate);
        for (String id : identifiers) {
            //valid check and zero padding?
            String type = getType(id).name();
            if (type.equals("ISBN13") || type.equals("ISBN10") || type.equals("ISMN")) type = "ISBN";
            if (type.equals("ISSNEAN13")) {
                type = "ISSN";
                id = issnFromEan13(id);
            }
            System.out.println(id + " "+ type );

            //WriteResults.writeLine(id);
            RestCall.callApi(id, type, licence, key);
            ArrayList<String> newEntry = new ArrayList<String>();
            newEntry.add(id);
            newEntry.add(type);
            newEntry.add("True");
            results.add(newEntry);
            //extract results
            //write results to csv
        }


    }




//
//            3. set up out file
//            4. read identifiers
//            for each identifier
//                add type
//                        send to service
//                write results
//                        5. write summary
//
//


}
