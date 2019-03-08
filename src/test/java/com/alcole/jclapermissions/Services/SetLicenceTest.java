package com.alcole.jclapermissions.Services;

import org.junit.Test;

import static com.alcole.jclapermissions.Services.SetLicence.getLicence;
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
        assertTrue(SetLicence.validateLicenceCode("143"));
        assertTrue(SetLicence.validateLicenceCode("230"));

        assertFalse(SetLicence.validateLicenceCode("111"));
    }
}
