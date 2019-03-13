package com.alcole.jclapermissions.Services;

import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URISyntaxException;

public class RestCall {

  private static int messageIdCounter = 0;

  public static String callApi(
      String identifier, String identifierType, String licenceTypeId, String key)
      throws IOException, URISyntaxException {

    String uri =
        String.format(
            "https://api.cla.co.uk/check-permissions/v1/GetPermissionByIdentifier/%s/%s/%s?messageId=%s&usageTypes=1,2,8&htmlToggle=False",
            identifierType, identifier, licenceTypeId, String.valueOf(messageIdCounter++));

    String result =
        Request.Get(uri)
            .connectTimeout(1000)
            .setHeader("Ocp-Apim-Subscription-Key", key)
            // .socketTimeout(1000)
            .execute()
            .returnContent()
            .asString();

    return result;
  }

  public static int getMessageIdCounter() {
    return messageIdCounter;
  }
}
