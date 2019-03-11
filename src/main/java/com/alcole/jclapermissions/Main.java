package com.alcole.jclapermissions;

import com.alcole.bibliotools.IsnLib;
import com.alcole.jclapermissions.Services.ReadIdentifiers;
import com.alcole.jclapermissions.Services.SetLicence;
import com.alcole.jclapermissions.Services.WriteResults;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.alcole.bibliotools.IsnLib.getType;
import static com.alcole.bibliotools.IsnLib.issnFromEan13;
import static com.alcole.jclapermissions.Services.LoadKey.getKey;

public class Main {

    private static Date date = Calendar.getInstance().getTime();
    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    private static String strDate = dateFormat.format(date);
    private static String resultsFileName = strDate + "_results.csv";
    private static String logfile = strDate + "_log.txt";

    private static int issnCount = 0;
    private static int isbnCount = 0;
    private static int invalidIsnCount = 0;

    private static List<String> identifiers = new ArrayList<>();
    static ResultList results = new ResultList();

    public static void main(String args[]) throws IOException, URISyntaxException {
        WriteLog.setupFile(logfile);

        String key = getKey();
        if (key.length() > 6) WriteLog.appendLine(logfile, "key read");
        else WriteLog.appendLine(logfile, "key issue, check key.txt");

        String licence = SetLicence.getLicence(); // check if licence is valid
        if (SetLicence.validateLicenceCode(licence)) {
            WriteLog.appendLine(logfile, "licence read ok: " + licence);
        } else {
            WriteLog.appendLine(logfile, "Invalid licence code: " + licence);
        }

        identifiers = ReadIdentifiers.getIdentifiers();
        WriteLog.appendLine(logfile, "" + identifiers.size() + " identifiers read");

        for (String id : identifiers) {
            //valid check
            if (!IsnLib.validateIsn(id)) {
                invalidIsnCount++;
                WriteLog.appendLine(logfile, "" + id + " fails check digit validation");
            } else {
                String type = getType(id).name();
                if (type.equals("ISBN13") || type.equals("ISBN10") || type.equals("ISMN")) {
                    type = "ISBN";
                    isbnCount++;
                } else if (type.equals("ISSNEAN13")) {
                    type = "ISSN";
                    id = issnFromEan13(id);
                    issnCount++;
                } else if (type.equals("ISSN")) issnCount++;
                System.out.println(id + " " + type);

                results.add(ReadJson.readJson(RestCall.callApi(id, type, licence, key).getContent(), id));
            }
        }
        WriteResults.write(resultsFileName, results);
//        for (Object result : results) {
//            System.out.println(result);
//        }

        WriteLog.appendLine(logfile, RestCall.getMessageIdCounter() + " permissions checked");
        WriteLog.appendLine(logfile, isbnCount + " ISBNs");
        WriteLog.appendLine(logfile, issnCount + " ISSNs");
        WriteLog.appendLine(logfile, invalidIsnCount > 0 ? invalidIsnCount + " invalid ISNs" : "No invalid ISNs");
    }

}
