package com.alcole.jclapermissions;

import com.alcole.bibliotools.IsnLib;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.alcole.bibliotools.IsnLib.getType;
import static com.alcole.bibliotools.IsnLib.issnFromEan13;

//invalid count
//isbn count, issn count
//included count, excluded count, nf count => summarised

public class Main  {


    private static Date date = Calendar.getInstance().getTime();
    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    private static String strDate = dateFormat.format(date);
    private static int issnCount = 0;
    private static int isbnCount = 0;
    private static int invalidIsnCount = 0;


    private static List<String> identifiers = new ArrayList<>();
   // private static List<List<String>> results2 = new ArrayList<List<String>>();
    //static ResultList results = new ResultList();



    public static void main(String args[]) throws IOException, URISyntaxException {
        //URI logfile = URI.create(strDate + "_log.txt");
        String logfile = strDate + "_log.txt";
        WriteLog.setupFile(logfile);
        String key = Key.getKey(); // check if read and write accordingly
        String licence = SetLicence.getLicence(); // check if licence is valid
        WriteLog.appendLine(logfile, "licence read ok: " + licence);

        identifiers = ReadIdentifiers.getIdentifiers();
        WriteLog.appendLine(logfile, "" + identifiers.size() + " identifiers read");
        System.out.println(key + " : " + licence);
        System.out.println(strDate);

        ArrayList<PermissionResult> r2 = new ArrayList<>();

        for (String id : identifiers) {
            //valid check and zero padding?
            if (!IsnLib.validateIsn(id)) {
                invalidIsnCount++;
            }
            else {
                String type = getType(id).name();
                if (type.equals("ISBN13") || type.equals("ISBN10") || type.equals("ISMN")) {
                    type = "ISBN";
                    isbnCount++;
                }
                else if (type.equals("ISSNEAN13")) {
                    type = "ISSN";
                    id = issnFromEan13(id);
                    issnCount++;
                }
                else if (type.equals("ISSN")) issnCount++;
                System.out.println(id + " "+ type );

                r2.add(ReadJson.readJson(RestCall.callApi(id, type, licence, key).getContent(), id));
            }
        }
        WriteResults.write(strDate, r2);
        for (Object result : r2) {
            System.out.println(result);
        }
        WriteLog.appendLine(logfile, RestCall.getMessageIdCounter() + " identifiers checked");
        WriteLog.appendLine(logfile, isbnCount + " ISBNs checked");
        WriteLog.appendLine(logfile, issnCount + " ISSNs checked");
        WriteLog.appendLine(logfile, invalidIsnCount + " invalid ISNs");
    }

}
