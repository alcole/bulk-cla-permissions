package com.alcole.jclapermissions.Services;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LicenceServiceTest {

  @BeforeClass
  public static void beforeTest() {
    System.out.println("Starting LicenceServiceTest");
  }

  @AfterClass
  public static void afterTest() {
    System.out.println("Ending LicenceServiceTest");
  }

  @Test
  public void ReadLicenceTest() {
    assertNotNull(LicenceService.getLicenceCode());
  }

  @Test
  public void isValidLicenceCodeTest() {
    assertTrue(LicenceService.isValidLicenceCode("132"));
    assertTrue(LicenceService.isValidLicenceCode("134"));
    assertTrue(LicenceService.isValidLicenceCode("136"));
    assertTrue(LicenceService.isValidLicenceCode("137"));
    assertTrue(LicenceService.isValidLicenceCode("140"));
    assertTrue(LicenceService.isValidLicenceCode("141"));
    assertTrue(LicenceService.isValidLicenceCode("143"));
    assertTrue(LicenceService.isValidLicenceCode("154"));
    assertTrue(LicenceService.isValidLicenceCode("230"));
    assertTrue(LicenceService.isValidLicenceCode("232"));
    assertTrue(LicenceService.isValidLicenceCode("234"));
    assertTrue(LicenceService.isValidLicenceCode("235"));

    assertFalse(LicenceService.isValidLicenceCode("111"));
  }

  @Test
  public void GetLicenceNameTest() {
    assertEquals("Schools", LicenceService.getLicenceName("143"));
    assertEquals("Code not found", LicenceService.getLicenceName("123"));
  }
}
