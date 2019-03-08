package com.alcole.jclapermissions;// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RestCall {

    private static int messageIdCounter = 0;

    public static HttpEntity callApi(String identifier, String identifierType, String licenceTypeId, String key) throws IOException, URISyntaxException {

        HttpClient httpclient = HttpClients.createDefault();

        URIBuilder builder = new URIBuilder("https://api.cla.co.uk/check-permissions/v1/GetPermissionByIdentifier/" + identifierType + "/" + identifier + "/" + licenceTypeId);
        builder.setParameter("messageId", String.valueOf(messageIdCounter++));
        builder.setParameter("usageTypes", "1,2,8");
        builder.setParameter("htmlToggle", "False");

        URI uri = builder.build();
        HttpGet request = new HttpGet(uri);
        request.setHeader("Ocp-Apim-Subscription-Key", key);

        System.out.println(request.toString() + " " + request.containsHeader("Ocp-Apim-Subscription-Key"));

        HttpResponse response = httpclient.execute(request);
        HttpEntity entity = response.getEntity();
        return entity;
    }

    public static int getMessageIdCounter() {
        return messageIdCounter;
    }
}
