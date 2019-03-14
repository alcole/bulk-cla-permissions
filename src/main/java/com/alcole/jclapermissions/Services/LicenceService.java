package com.alcole.jclapermissions.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class LicenceService {

  private static LicenceService instance = null;

  private static HashMap<String, String> licences = new HashMap<>();

  static {
    licences.put("136", "Higher Education");
    licences.put("132", "Further Education");
    licences.put("134", "Business");
    licences.put("137", "Law");
    licences.put("140", "Extended Multinational");
    licences.put("141", "Pharmaceutical");
    licences.put("154", "Public Sector");
    licences.put("143", "Schools");
    licences.put("230", "Extended Pharmaceutical Multinational");
    licences.put("232", "Media Monitoring");
    licences.put("234", "Multinational");
    licences.put("235", "Pharmaceutical Multinational");
  }

  private String licenceCode;

  private LicenceService() {
    Path licenceFile = Paths.get("licence.txt");
    if (Files.exists(licenceFile)) {
      try (BufferedReader reader = Files.newBufferedReader(licenceFile)) {
        licenceCode = reader.readLine().trim();
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static String getLicenceCode() {
    if (instance == null) instance = new LicenceService();
    return instance.licenceCode;
  }

  public static boolean validateLicenceCode(String code) {
    return licences.containsKey(code);
  }

  public static String getLicenceName(String code) {
    return licences.getOrDefault(code, "Code not found");
  }
}
