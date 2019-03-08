package com.alcole.jclapermissions;

import com.alcole.jclapermissions.Services.LoadKey;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class RestCallTest {

    private static String key;


    @BeforeClass
    public static void beforeTest() {
        System.out.println("Starting LoadKeyTest");
    }

    @AfterClass
    public static void afterTest() {
        System.out.println("Ending LoadKeyTest");
    }

    @Before
    public void getKey() {
        try {
            key = LoadKey.getKey();
        }
        catch (IOException e) {
            e.getMessage();
        }
    }

//    @Test
//    public void keyNotNullTest() {
//        RestCall.callApi("00014664", "ISSN", "136", key);
//    }
}
