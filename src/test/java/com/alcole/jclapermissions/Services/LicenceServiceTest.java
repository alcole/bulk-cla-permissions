package com.alcole.jclapermissions.Services;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LicenceServiceTest {

  @Test
  public void ReadLicenceTest() {
    assertEquals(LicenceService.getLicenceCode(), "143");
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
}
