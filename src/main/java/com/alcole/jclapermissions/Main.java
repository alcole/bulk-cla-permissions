package com.alcole.jclapermissions;

import static com.alcole.bibliotools.IsnLib.getType;
import static com.alcole.bibliotools.IsnLib.issnFromEan13;
import static com.alcole.jclapermissions.Services.KeyService.getKey;

import com.alcole.bibliotools.IsnLib;
import com.alcole.jclapermissions.Services.LicenceService;
import com.alcole.jclapermissions.Services.ReadIdentifiers;
import com.alcole.jclapermissions.Services.ReadJson;
import com.alcole.jclapermissions.Services.RestService;
import com.alcole.jclapermissions.Services.ResultList;
import com.alcole.jclapermissions.Services.WriteLog;
import com.alcole.jclapermissions.Services.WriteResults;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {

  private static final Date date = Calendar.getInstance().getTime();
  private static final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
  private static final String strDate = dateFormat.format(date);
  private static final String resultsFileName = strDate + "_results.csv";
  private static final String logfile = strDate + "_log.txt";

  private static int issnCount = 0;
  private static int isbnCount = 0;
  private static int invalidIsnCount = 0;
  private static int successfulChecks = 0;

  private static final ResultList results = new ResultList();

  public static void main(String args[]) throws IOException, URISyntaxException {
    WriteLog.setupFile(logfile);

    final String key = getKey();
    if (key.length() > 6) {
      WriteLog.appendLine(logfile, "key read");
    } else {
      WriteLog.appendLine(logfile, "key issue, check key.txt");
    }

    // print licence code correponds to ... licence
    String licence = LicenceService.getLicenceCode();
    WriteLog.appendLine(logfile, LicenceService.getLicenceName(licence));
    if (LicenceService.validateLicenceCode(licence)) {
      WriteLog.appendLine(
          logfile,
          String.format(
              "licence read ok: %s: %s", licence, LicenceService.getLicenceName(licence)));
    } else {
      WriteLog.appendLine(logfile, "Invalid licence code: " + licence);
    }

    List<String> identifiers = new ArrayList<>();
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
        } else if (type.equals("ISSN")) {
          issnCount++;
        }
        try {
          results.add(ReadJson.readJson(RestService.getPermissions(id, type, licence, key), id));
          successfulChecks++;
        } catch (IOException e) {
          WriteLog.appendLine(logfile, "IOException: " + e.getMessage() + " on checking " + id);
          System.out.println(Arrays.toString(e.getStackTrace()));
        }
      }
    }
    WriteResults.write(resultsFileName, results);

    WriteLog.appendLine(logfile, successfulChecks + " permissions checked");
    if (invalidIsnCount > 0) {
      WriteLog.appendLine(
          logfile, invalidIsnCount + " invalid ISNs");
    } else {
      WriteLog.appendLine(
          logfile, "No invalid ISNs");
    }
    if (isbnCount == 1) {
      WriteLog.appendLine(logfile, isbnCount + " ISBN");
    } else {
      WriteLog.appendLine(logfile, isbnCount + " ISBNs");
    }
    if (issnCount == 1) {
      WriteLog.appendLine(logfile, issnCount + " ISSN");
    } else {
      WriteLog.appendLine(logfile, issnCount + " ISSNs");
    }
  }
}
