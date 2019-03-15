package com.alcole.jclapermissions.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * class to access the API Manager key.
 */
public final class KeyService {

  // create an object of SingleObject
  private static KeyService instance = null;

  /** the Ocp-Apim-Subscription-Key. */
  private String ocp = "";

  /**
   * reads in the key and sets to ocp variable.
   * @throws IOException if the file is not accessible
   */
  private KeyService() throws IOException {
    Path keyFile = Paths.get("key.txt");
    BufferedReader reader = Files.newBufferedReader(keyFile);
    ocp = reader.readLine().trim();
  }

  /**
   * get the key.
   * @return the OCM-API subscription key
   * @throws IOException if key is not accessible
   */
  public static String getKey() throws IOException {
    if (instance == null) {
      instance = new KeyService();
    }
    return instance.ocp;
  }
}
