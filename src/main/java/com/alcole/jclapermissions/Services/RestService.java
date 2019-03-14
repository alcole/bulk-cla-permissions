package com.alcole.jclapermissions.Services;

import java.io.IOException;
import org.apache.http.client.fluent.Request;

public final class RestService {

  private static final String PERMISSION_URL =
      "https://api.cla.co.uk/check-permissions/v1/GetPermissionByIdentifier/";
  private static final String ADDITIONAL_QUERY_PARAMETERS = "&usageTypes=1,2,8&htmlToggle=False";
  private static final int TIMEOUT_TIME = 1000;
  private static int messageIdCounter = 0;

  private RestService() {
    // hidden
  }

  public static String getPermissions(
      final String identifier,
      final String identifierType,
      final String licenceTypeId,
      final String key)
      throws IOException {

    String uri =
        String.format(
            PERMISSION_URL + "%s/%s/%s?messageId=%s" + ADDITIONAL_QUERY_PARAMETERS,
            identifierType,
            identifier,
            licenceTypeId,
            String.valueOf(messageIdCounter++));

    return Request.Get(uri)
        .connectTimeout(TIMEOUT_TIME)
        .setHeader("Ocp-Apim-Subscription-Key", key)
        .execute()
        .returnContent()
        .asString();
  }

  public static int getMessageIdCounter() {
    return messageIdCounter;
  }
}
