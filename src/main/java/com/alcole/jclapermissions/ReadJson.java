package com.alcole.jclapermissions;

import com.alcole.jclapermissions.Model.PermissionResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class ReadJson {

  static PermissionResult readJson(InputStream json, String identifier) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    // read JSON like DOM Parser
    JsonNode rootNode = objectMapper.readTree(json);
    JsonNode idNode = rootNode.path("metadata");

    // check status code and write to log if not ok?
    JsonNode permissionNode = rootNode.path("usagesSummary");

    PermissionResult result =
        PermissionResult.builder()
            .identifier(identifier)
            .title(idNode.path("title").textValue())
            .format(idNode.path("publicationForm").textValue())
            .publisher(idNode.path("publisher").textValue())
            .publisherCountry(idNode.path("publicationCountry").textValue())
            .build();

    if (permissionNode.get(0).get("usageType").textValue().equals("Photocopying"))
      result.setPhotocopyingPermission(permissionNode.get(0).get("reportType").textValue());
    if (permissionNode.get(1).get("usageType").textValue().equals("Scanning"))
      result.setScanningPermission(permissionNode.get(1).get("reportType").textValue());
    if (permissionNode.get(2).get("usageType").textValue().equals("Digital"))
      result.setDigitalPermission(permissionNode.get(2).get("reportType").textValue());
    return result;
  }
}
