package com.alcole.jclapermissions;// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RestCall


    {
        public static void callApi(String identifier, String identifierType, String licenceTypeId, String key) {

            HttpClient httpclient = HttpClients.createDefault();

            try {
//                URIBuilder builder = new URIBuilder("https://api.cla.co.uk/check-permissions/v1/GetPermissionByIdentifier/{identifierType}/{identifier}/{licenceTypeId}");
                URIBuilder builder = new URIBuilder("https://api.cla.co.uk/check-permissions/v1/GetPermissionByIdentifier/"+identifierType+"/"+identifier+"/"+licenceTypeId);
                builder.setParameter("messageId", "1");
                builder.setParameter("usageTypes", "1,2,8");
                builder.setParameter("htmlToggle", "False");

                URI uri = builder.build();
                System.out.println(uri);
                HttpGet request = new HttpGet(uri);
                request.setHeader("Ocp-Apim-Subscription-Key", key);


                HttpResponse response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();
                System.out.println(entity);

                if (entity != null) {
                    System.out.println(EntityUtils.toString(entity));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
