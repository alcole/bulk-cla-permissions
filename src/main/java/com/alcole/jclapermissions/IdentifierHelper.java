package com.alcole.jclapermissions;

public class IdentifierHelper {

    public static String classify(String identifier) {
        identifier = clean(identifier);
        if (identifier.length() == 13) return "ISBN";
        if (identifier.length() == 8) return "ISSN";
        else return "invalid";
    }

    public static String clean(String identifier) {
        return identifier.trim().replace("-","");
    }

}
