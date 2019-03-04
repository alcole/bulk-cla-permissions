package com.alcole.jclapermissions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

//import static com.alcole.jclapermissions.Main.results;

public class ReadJson {

    //public static void main(String[] args) {

    static PermissionResult readJson(InputStream json, String identifier) throws IOException{
        // https://www.journaldev.com/2324/jackson-json-java-parser-api-example-tutorial
  //      try {
            //byte[] jsonData = Files.readAllBytes(Paths.get("sample.json"));
            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            //read JSON like DOM Parser
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode idNode = rootNode.path("metadata");
            System.out.println("id = " + idNode);
            PermissionResult r1  = PermissionResult.builder()
                    .identifier(identifier)
                    .title(idNode.path("title").toString())
                    .format(idNode.path("publicationForm").toString())
                    .publisher(idNode.path("publisher").toString()).build();
            //System.out.println("ident = " + idNode.path("identifier"));
            System.out.println(r1.toString());
            JsonNode usage = rootNode.path("usagesSummary");
            System.out.println("id = " + usage);
            //results.add(r1);
            return r1;
    }

}
