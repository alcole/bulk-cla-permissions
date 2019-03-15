package com.alcole.jclapermissions.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public final class LicenceService {

  private static LicenceService instance = null;

  private static final HashMap<String, String> LICENCES = new HashMap<>();

  static {
    LICENCES.put("136", "Higher Education");
    LICENCES.put("132", "Further Education");
    LICENCES.put("134", "Business");
    LICENCES.put("137", "Law");
    LICENCES.put("140", "Extended Multinational");
    LICENCES.put("141", "Pharmaceutical");
    LICENCES.put("154", "Public Sector");
    LICENCES.put("143", "Schools");
    LICENCES.put("230", "Extended Pharmaceutical Multinational");
    LICENCES.put("232", "Media Monitoring");
    LICENCES.put("234", "Multinational");
    LICENCES.put("235", "Pharmaceutical Multinational");
  }

  private String licenceCode;

  private LicenceService() {
    Path licenceFile = Paths.get("licenceCode.txt");
    if (Files.exists(licenceFile)) {
      try (BufferedReader reader = Files.newBufferedReader(licenceFile)) {
        licenceCode = reader.readLine().trim();
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static String getLicenceCode() {
    if (instance == null) {
      instance = new LicenceService();
    }
    return instance.licenceCode;
  }

  public static boolean isValidLicenceCode(final String code) {
    return LICENCES.containsKey(code);
  }

  public static String getLicenceName(final String code) {
    return LICENCES.getOrDefault(code, "Code not found");
  }
}
