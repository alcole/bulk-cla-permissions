package com.alcole.jclapermissions.Services;

import java.io.IOException;
import org.apache.http.client.fluent.Request;

/** class to manage the API calls. */
public final class RestService {

  private static final String PERMISSION_URL =
      "https://api.cla.co.uk/check-permissions/v1/GetPermissionByIdentifier/";
  private static final String ADDITIONAL_QUERY_PARAMETERS = "&usageTypes=1,2,8&htmlToggle=False";
  private static final int TIMEOUT_TIME = 1000;
  private static int messageIdCounter = 0;
  private static String apimKey;
  private static String licenceTypeId;

  private RestService() {
    // hidden
  }

  /**
   * calls the CLA API with getPermissionByIdentifier.
   *
   * @param identifier the identifier to retrieve permissions for
   * @param identifierType the type of the identifier
   * @return the content of the response as a String
   * @throws IOException when the call fails
   */
  public static String getPermissions(final String identifier, final String identifierType)
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
        .setHeader("Ocp-Apim-Subscription-Key", apimKey)
        .execute()
        .returnContent()
        .asString();
  }

  /**
   * sets the Ocp-Apim-Subscription-Key for use in the API calls.
   *
   * @param key the key to use
   */
  public static void setKey(final String key) {
    apimKey = key;
  }

  /**
   * sets the licence type code to use in the API calls.
   *
   * @param licence licence type code
   */
  public static void setLicence(final String licence) {
    licenceTypeId = licence;
  }

  /**
   * get the number of times the API was called.
   *
   * @return number of calls
   */
  public static int getMessageIdCounter() {
    return messageIdCounter;
  }
}
