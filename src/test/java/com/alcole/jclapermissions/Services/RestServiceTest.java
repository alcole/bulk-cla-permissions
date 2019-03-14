package com.alcole.jclapermissions.Services;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.alcole.jclapermissions.Services.RestService.getPermissions;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RestServiceTest {

  @Test
  public void getPermissionsTest() throws IOException, URISyntaxException {
    String result = getPermissions("9781408855652", "ISBN", "136", LoadKey.getKey());
    System.out.println(result);
    assertNotNull(result);
  }
}
