package com.alcole.jclapermissions.Services;

import com.alcole.jclapermissions.Model.PermissionResult;
import com.alcole.jclapermissions.Services.WriteResults;
import org.junit.BeforeClass;
import org.junit.Test;

public class WriteResultsTest {

    static ResultList results;

    @BeforeClass
    public static void beforeTest() {
        results = new ResultList();
        results.add( PermissionResult.builder()
                        .identifier("00000000")
                        .title("title with commas, and one, more")
                        .format("print")
                        .publisher("self")
                        .publisherCountry("UK")
                        .photocopyingPermission("Grant")
                        .scanningPermission("Warning")
                        .digitalPermission("Exclude")
                        .build());
    }

    @Test
    public void writeTest() {
        WriteResults.write("testout.csv", results);
    }
}
