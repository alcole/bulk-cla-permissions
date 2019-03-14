package com.alcole.jclapermissions.Services;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

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
  public void ValidateLicenceCode() {
    assertTrue(LicenceService.validateLicenceCode("132"));
    assertTrue(LicenceService.validateLicenceCode("134"));
    assertTrue(LicenceService.validateLicenceCode("136"));
    assertTrue(LicenceService.validateLicenceCode("137"));
    assertTrue(LicenceService.validateLicenceCode("140"));
    assertTrue(LicenceService.validateLicenceCode("141"));
    assertTrue(LicenceService.validateLicenceCode("143"));
    assertTrue(LicenceService.validateLicenceCode("154"));
    assertTrue(LicenceService.validateLicenceCode("230"));
    assertTrue(LicenceService.validateLicenceCode("232"));
    assertTrue(LicenceService.validateLicenceCode("234"));
    assertTrue(LicenceService.validateLicenceCode("235"));

    assertFalse(LicenceService.validateLicenceCode("111"));
  }

  @Test
  public void GetLicenceNameTest() {
    assertEquals("Schools", LicenceService.getLicenceName("143"));
    assertEquals("Code not found", LicenceService.getLicenceName("123"));
  }
}
