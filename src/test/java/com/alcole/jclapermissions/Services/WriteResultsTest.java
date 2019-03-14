package com.alcole.jclapermissions.Services;

import com.alcole.jclapermissions.Model.PermissionResult;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class WriteResultsTest {

  static private ResultList results;

  @BeforeClass
  public static void beforeTest() {
    System.out.println("Starting WriteResultsTest");
    results = new ResultList();
    results.add(
        PermissionResult.builder()
            .identifier("00000000")
            .title("title with commas, and one, more")
            .format("print")
            .publisher("self")
            .publisherCountry("UK")
            .photocopyingPermission("Grant")
            .scanningPermission("Warning")
            .digitalPermission("Exclude")
            .build());
  }

  @AfterClass
  public static void afterTest() {
    System.out.println("WriteResultsTest");
  }

  @Test
  public void writeTest() {
    WriteResults.write("testout.csv", results);
  }
}
