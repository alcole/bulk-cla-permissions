package com.alcole.jclapermissions.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class KeyService {

  // create an object of SingleObject
  private static KeyService instance = null;

  private String ocp = "";

  // make the constructor private so that this class cannot be
  // instantiated
  private KeyService() throws IOException {
    Path keyFile = Paths.get("key.txt");
    BufferedReader reader = Files.newBufferedReader(keyFile);
    ocp = reader.readLine().trim();
  }

  // Get the only object available
  public static String getKey() throws IOException {
    if (instance == null) {
      instance = new KeyService();
    }
    return instance.ocp;
  }
}
