package com.alcole.jclapermissions;

import com.alcole.bibliotools.IsnLib;
import com.alcole.jclapermissions.Services.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.alcole.bibliotools.IsnLib.getType;
import static com.alcole.bibliotools.IsnLib.issnFromEan13;
import static com.alcole.jclapermissions.Services.LoadKey.getKey;

public class Main {

  private static Date date = Calendar.getInstance().getTime();
  private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
  private static String strDate = dateFormat.format(date);
  private static final String resultsFileName = strDate + "_results.csv";
  private static final String logfile = strDate + "_log.txt";

  private static int issnCount = 0;
  private static int isbnCount = 0;
  private static int invalidIsnCount = 0;
    private static int successfulChecks = 0;

  private static List<String> identifiers = new ArrayList<>();
  static ResultList results = new ResultList();

  public static void main(String args[]) throws IOException, URISyntaxException {
    WriteLog.setupFile(logfile);

    final String key = getKey();
    if (key.length() > 6) WriteLog.appendLine(logfile, "key read");
    else WriteLog.appendLine(logfile, "key issue, check key.txt");

    // print licence code correponds to ... licence
    String licence = SetLicence.getLicence();
    if (SetLicence.validateLicenceCode(licence)) {
      WriteLog.appendLine(logfile, "licence read ok: " + licence);
    } else {
      WriteLog.appendLine(logfile, "Invalid licence code: " + licence);
    }

    identifiers = ReadIdentifiers.getIdentifiers();
    WriteLog.appendLine(logfile, "" + identifiers.size() + " identifiers read");

    for (String id : identifiers) {
        // valid check
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
            try {
                results.add(ReadJson.readJson(RestService.getPermissions(id, type, licence, key), id));
                successfulChecks++;
            } catch (IOException e) {
                WriteLog.appendLine(logfile, "IOException: " + e.getMessage() + " on checking " + id);
                System.out.println(Arrays.toString(e.getStackTrace()));
                continue;
            }
        }
    }
    //    } finally {
    WriteResults.write(resultsFileName, results);

    WriteLog.appendLine(logfile, successfulChecks + " permissions checked");
      WriteLog.appendLine(
              logfile, invalidIsnCount > 0 ? invalidIsnCount + " invalid ISNs" : "No invalid ISNs");
    WriteLog.appendLine(logfile, isbnCount == 1 ? isbnCount + "ISBN checked" : isbnCount + " ISBNs checked");
    WriteLog.appendLine(logfile, issnCount == 1 ? issnCount + "ISSN checked" : issnCount + " ISSNs checked");

  }
}
