package com.alcole.jclapermissions.Services;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SetLicenceTest {

  @Test
  public void ReadLicenceTest() {
    assertEquals(SetLicence.getLicence(), "136");
  }

  @Test
  public void ValidateLicenceCode() {
    assertTrue(SetLicence.validateLicenceCode("132"));
    assertTrue(SetLicence.validateLicenceCode("134"));
    assertTrue(SetLicence.validateLicenceCode("136"));
    assertTrue(SetLicence.validateLicenceCode("137"));
    assertTrue(SetLicence.validateLicenceCode("140"));
    assertTrue(SetLicence.validateLicenceCode("141"));
    assertTrue(SetLicence.validateLicenceCode("143"));
    assertTrue(SetLicence.validateLicenceCode("154"));
    assertTrue(SetLicence.validateLicenceCode("230"));
    assertTrue(SetLicence.validateLicenceCode("232"));
    assertTrue(SetLicence.validateLicenceCode("234"));
    assertTrue(SetLicence.validateLicenceCode("235"));

    assertFalse(SetLicence.validateLicenceCode("111"));
  }
}
