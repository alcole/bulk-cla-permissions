package com.alcole.jclapermissions;


import lombok.*;

@Builder
@Getter
@Setter
//@ToString
public class PermissionResult {

    private String title;
    private String identifier;
    private String format;
    private String publisher;
    private String publisherCountry;

    private String photocopyingPermission;
    private String scanningPermission;
    private String digitalPermission;

    String[] toArray() {
        String[] array = {identifier, title, format, publisher, publisherCountry
                , photocopyingPermission, scanningPermission, digitalPermission};
        return array;
    }

    @Override
    public String toString() {
        return identifier + "," +title + "," +  format + "," + publisher;
    }


}
