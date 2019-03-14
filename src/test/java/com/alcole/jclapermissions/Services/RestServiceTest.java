package com.alcole.jclapermissions.Services;

import static com.alcole.jclapermissions.Services.RestService.getPermissions;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RestServiceTest {

  @BeforeClass
  public static void beforeTest() {
    System.out.println("Starting RestServiceTest");
  }

  @AfterClass
  public static void afterTest() {
    System.out.println("Ending RestServiceTest");
  }

  @Test
  public void getPermissionsTest() throws IOException, URISyntaxException {
    String result = getPermissions("9781408855652", "ISBN", "136", KeyService.getKey());
    System.out.println(result);
    assertNotNull(result);
  }
}
