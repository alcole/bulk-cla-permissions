package com.alcole.jclapermissions.Services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.alcole.jclapermissions.Model.PermissionResult;
import org.junit.Before;
import org.junit.Test;

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
