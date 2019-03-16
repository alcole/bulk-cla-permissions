package com.alcole.jclapermissions.Services;

import com.alcole.jclapermissions.Model.PermissionResult;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

/** class to manage the API calls. */
public final class RestService {

  private static final String PERMISSION_URL =
      "https://api.cla.co.uk/check-permissions/v1/GetPermissionByIdentifier/";
  private static final String ADDITIONAL_QUERY_PARAMETERS = "&usageTypes=1,2,8&htmlToggle=False";
  private static final int TIMEOUT_TIME = 1000;
  private static int messageIdCounter = 0;
  private static String apimKey;
  private static String licenceTypeId;

  private static CloseableHttpClient httpClient =
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
  public static PermissionResult getPermissions(final String identifier, final String identifierType)
      throws IOException, URISyntaxException {

    URIBuilder builder =
        new URIBuilder(PERMISSION_URL + identifierType + "/" + identifier + "/" + licenceTypeId);
    builder.setParameter("messageId", String.valueOf(messageIdCounter++));
    builder.setParameter("usageTypes", "1,2,8");
    builder.setParameter("htmlToggle", "False");

    URI uri = builder.build();
    HttpGet request = new HttpGet(uri);
    request.setHeader("Ocp-Apim-Subscription-Key", apimKey);

    PermissionResult result = httpClient.execute(request, rh);
    result.setIdentifier(identifier);
    return result;
  }

  private static ResponseHandler<PermissionResult> rh = new ResponseHandler<PermissionResult>() {

    @Override
    public PermissionResult handleResponse(
        final HttpResponse response) throws IOException {
      StatusLine statusLine = response.getStatusLine();
      HttpEntity entity = response.getEntity();
      if (statusLine.getStatusCode() >= 300) {
        throw new HttpResponseException(
            statusLine.getStatusCode(),
            statusLine.getReasonPhrase());
      }
      if (entity == null) {
        throw new ClientProtocolException("Response contains no content");
      }
      JsonFactory jsonf = new JsonFactory();
      InputStream instream = entity.getContent();
      // try - finally is not strictly necessary here
      // but is a good practice
      try {
        JsonParser jsonParser = jsonf.createParser(instream);
        // Use the parser to deserialize the object from the content stream
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonParser);

        JsonNode idNode = rootNode.path("metadata");

        // check status code and write to log if not ok?
        JsonNode permissionNode = rootNode.path("usagesSummary");

        PermissionResult result =
            PermissionResult.builder()
                .title(idNode.path("title").textValue())
                .format(idNode.path("publicationForm").textValue())
                .publisher(idNode.path("publisher").textValue())
                .publisherCountry(idNode.path("publicationCountry").textValue())
                .build();

        if (permissionNode.get(0).get("usageType").textValue().equals("Photocopying")) {
          result.setPhotocopyingPermission(permissionNode.get(0).get("reportType").textValue());
        }
        if (permissionNode.get(1).get("usageType").textValue().equals("Scanning")) {
          result.setScanningPermission(permissionNode.get(1).get("reportType").textValue());
        }
        if (permissionNode.get(2).get("usageType").textValue().equals("Digital")) {
          result.setDigitalPermission(permissionNode.get(2).get("reportType").textValue());
        }
        return result;
      }  finally {
        instream.close();
      }
    }
  };


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
