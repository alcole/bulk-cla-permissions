package com.alcole.jclapermissions;

import static com.alcole.bibliotools.IsnLib.getType;
import static com.alcole.bibliotools.IsnLib.issnFromEan13;
import static com.alcole.jclapermissions.Services.KeyService.getKey;
import static com.alcole.jclapermissions.Services.RestService.getMessageIdCounter;
import static com.alcole.jclapermissions.Services.RestService.setKey;
import static com.alcole.jclapermissions.Services.RestService.setLicence;

import com.alcole.bibliotools.IsnLib;
import com.alcole.jclapermissions.Services.LicenceService;
import com.alcole.jclapermissions.Services.ReadIdentifiers;
import com.alcole.jclapermissions.Services.ReadJson;
import com.alcole.jclapermissions.Services.RestService;
import com.alcole.jclapermissions.Services.ResultList;
import com.alcole.jclapermissions.Services.WriteLog;
import com.alcole.jclapermissions.Services.WriteResults;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {

  private static final Date DATE = Calendar.getInstance().getTime();
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddhhmmss");
  private static final String STR_DATE = DATE_FORMAT.format(DATE);
  private static final String RESULTS_FILE_NAME = STR_DATE + "_results.csv";
  private static final String LOGFILE = STR_DATE + "_log.txt";
  private static final ResultList results = new ResultList();
  private static int issnCount = 0;
  private static int isbnCount = 0;
  private static int invalidIsnCount = 0;
  private static int successfulChecks = 0;

  public static void main(String[] args) throws Exception {
    WriteLog.setupFile(LOGFILE);

    final String key = getKey();
    if (key.isEmpty()) {
      WriteLog.appendLine("key issue, check key.txt");
      throw new Exception("key failure");
    } else {
      WriteLog.appendLine("key read");
      setKey(key);
    }

    String licence = LicenceService.getLicenceCode();
    if (LicenceService.isValidLicenceCode(licence)) {
      WriteLog.appendLine(
          String.format(
              "licence read ok: %s: %s", licence, LicenceService.getLicenceName(licence)));
      setLicence(licence);
    } else {
      WriteLog.appendLine("Invalid licence code: " + licence);
      throw new Exception("licence read failure");
    }

    List<String> identifiers = ReadIdentifiers.getIdentifiers();
    WriteLog.appendLine("" + identifiers.size() + " identifiers read");

    for (String id : identifiers) {
      // valid check
      if (!IsnLib.isValidIsn(id)) {
        invalidIsnCount++;
        WriteLog.appendLine("" + id + " fails check digit validation");
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
          results.add(RestService.getPermissions(id, type));
          System.out.println("checking " + id);
          successfulChecks++;
        } catch (IOException e) {
          WriteLog.appendLine("IOException: " + e.getMessage() + " on checking " + id);
          System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (NullPointerException e) {
          WriteLog.appendLine("NullPointerException: " + e.getMessage() + " on checking " + id);
          System.out.println(Arrays.toString(e.getStackTrace()));
        }
      }
    }
    WriteResults.write(RESULTS_FILE_NAME, results);

    WriteLog.appendLine(
        successfulChecks + " permissions checked from " + getMessageIdCounter() + " calls");
    if (invalidIsnCount > 0) {
      WriteLog.appendLine(invalidIsnCount + " invalid ISNs");
    } else {
      WriteLog.appendLine("No invalid ISNs");
    }
    if (isbnCount == 1) {
      WriteLog.appendLine(isbnCount + " ISBN");
    } else {
      WriteLog.appendLine(isbnCount + " ISBNs");
    }
    if (issnCount == 1) {
      WriteLog.appendLine(issnCount + " ISSN");
    } else {
      WriteLog.appendLine(issnCount + " ISSNs");
    }
  }
}
