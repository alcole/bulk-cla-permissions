package com.alcole.jclapermissions.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

/** class to manage the API calls. */
public final class RestService {

  private static final String PERMISSION_URL =
      "https://api.cla.co.uk/check-permissions/v1/GetPermissionByIdentifier/";
  private static final String ADDITIONAL_QUERY_PARAMETERS = "&usageTypes=1,2,8&htmlToggle=False";
  private static final int TIMEOUT_TIME = 1000;
  private static int messageIdCounter = 0;
  private static String apimKey;
  private static String licenceTypeId;

  private static HttpClient httpClient =
      HttpClients.custom()
          .setDefaultRequestConfig(RequestConfig.custom().setCookieSpec("easy").build())
          .build();

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
  public static HttpEntity getPermissions(final String identifier, final String identifierType)
      throws IOException, URISyntaxException {

    URIBuilder builder =
        new URIBuilder(PERMISSION_URL + identifierType + "/" + identifier + "/" + licenceTypeId);
    builder.setParameter("messageId", String.valueOf(messageIdCounter++));
    builder.setParameter("usageTypes", "1,2,8");
    builder.setParameter("htmlToggle", "False");

    URI uri = builder.build();
    HttpGet request = new HttpGet(uri);
    request.setHeader("Ocp-Apim-Subscription-Key", apimKey);

    HttpResponse response = httpClient.execute(request);

    return response.getEntity();
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
