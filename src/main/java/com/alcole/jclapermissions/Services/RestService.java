package com.alcole.jclapermissions.Services;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import java.io.IOException;
import java.net.URISyntaxException;

public class RestService {

  private static int messageIdCounter = 0;
  private static final String PERMISSION_URL =
      "https://api.cla.co.uk/check-permissions/v1/GetPermissionByIdentifier/";
  private static final String ADDITIONAL_QUERY_PARAMETERS = "&usageTypes=1,2,8&htmlToggle=False";

  public static String getPermissions(
      String identifier, String identifierType, String licenceTypeId, String key)
      throws IOException, URISyntaxException {

    String uri =
        String.format(
            PERMISSION_URL + "%s/%s/%s?messageId=%s" + ADDITIONAL_QUERY_PARAMETERS,
            identifierType,
            identifier,
            licenceTypeId,
            String.valueOf(messageIdCounter++));

    String result =
        Request.Get(uri)
            .connectTimeout(1000)
            .setHeader("Ocp-Apim-Subscription-Key", key)
            .execute()
            .returnContent()
            .asString();

    return result;
  }

  public static int getMessageIdCounter() {
    return messageIdCounter;
  }
}
