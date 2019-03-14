package com.alcole.jclapermissions.Services;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.alcole.jclapermissions.Services.RestService.getPermissions;
import static org.junit.Assert.*;

public class RestServiceTest {

  @Test
  public void getPermissionsTest() throws IOException, URISyntaxException {
    String result = getPermissions("9781408855652", "ISBN", "136", KeyService.getKey());
    System.out.println(result);
    assertNotNull(result);
  }
}
