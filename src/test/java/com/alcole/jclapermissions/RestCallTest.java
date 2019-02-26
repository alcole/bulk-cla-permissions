package com.alcole.jclapermissions;

import com.alcole.jclapermissions.RestCall;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class RestCallTest {

    private static String key;


    @BeforeClass
    public static void beforeTest() {
        System.out.println("Starting KeyTest");
    }

    @AfterClass
    public static void afterTest() {
        System.out.println("Ending KeyTest");
    }

    @Before
    public void getKey() {
        key  = Key.getKey();
    }

    @Test
    public void keyNotNullTest() {
        RestCall.callApi("00014664", "ISSN", "136", key);
    }
}
