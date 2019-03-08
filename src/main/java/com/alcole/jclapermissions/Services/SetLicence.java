package com.alcole.jclapermissions.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class SetLicence {

    private static SetLicence instance = null;

    private static HashSet<String> licences = new HashSet<>();
    static {
        licences.add("136");
        licences.add("132");
        licences.add("134");
        licences.add("137");
        licences.add("140");
        licences.add("141");
        licences.add("154");
        licences.add("134");
        licences.add("143");
        licences.add("230");
        licences.add("232");
        licences.add("234");
        licences.add("235");
    }

    private String licenceCode = "136";


    private SetLicence(){
        Path licenceFile = Paths.get("licence.txt");
        if(Files.exists(licenceFile)) {
            try (BufferedReader reader =
                         Files.newBufferedReader(licenceFile)) {
                licenceCode = reader.readLine().trim();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getLicence() {
        if (instance == null) instance = new SetLicence();
        return instance.licenceCode;
    }

    public static boolean validateLicenceCode(String code) {
        return licences.contains(code);
    }



}

