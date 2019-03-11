package com.alcole.jclapermissions.Services;

import com.alcole.jclapermissions.Model.PermissionResult;
import com.alcole.jclapermissions.ResultList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResultListTest {

  private ResultList results;
  private PermissionResult result1;

  @Before
  public void initTestCase() {
    results = new ResultList();
    result1 =
        PermissionResult.builder()
            .identifier("00000000")
            .title("title")
            .format("print")
            .publisher("self")
            .publisherCountry("UK")
            .photocopyingPermission("Grant")
            .scanningPermission("Warning")
            .digitalPermission("Exclude")
            .build();
  }

  @Test
  public void constructorTest() {
    assertNotNull(results);
  }

  @Test
  public void addResultTest() {
    assertTrue(results.add(result1));
  }
}
