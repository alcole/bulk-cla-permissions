package com.alcole.jclapermissions.Services;

import static com.alcole.jclapermissions.Services.RestService.getPermissions;
import static com.alcole.jclapermissions.Services.RestService.setKey;
import static com.alcole.jclapermissions.Services.RestService.setLicence;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RestServiceTest {

  @BeforeClass
  public static void beforeTest() throws IOException {
    System.out.println("Starting RestServiceTest");
    setKey(KeyService.getKey());
    setLicence("136");
  }

  @AfterClass
  public static void afterTest() {
    System.out.println("Ending RestServiceTest");
  }

  @Test
  public void getPermissionsTest() throws IOException, URISyntaxException {
//    String result = getPermissions("9781408855652", "ISBN");
//    System.out.println(result);
    System.out.println();
//    assertNotNull(result);
  }
}
