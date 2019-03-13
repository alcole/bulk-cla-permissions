package com.alcole.jclapermissions.Services;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.alcole.jclapermissions.Services.RestCall.callApi;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RestCallTest {

  @Test
  public void callApiTest() throws IOException, URISyntaxException {
    String result = callApi("9781408855652", "ISBN", "136", LoadKey.getKey());
    System.out.println(result);
    assertNotNull(result);
  }
}
