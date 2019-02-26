package com.alcole.jclapermissions;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

public class KeyTest {



    @BeforeClass
    public static void beforeTest() {
        System.out.println("Starting KeyTest");
    }

    @AfterClass
    public static void afterTest() {
        System.out.println("Ending KeyTest");
    }


    @Test
    public void keyNotNullTest() {
        assertNotNull(Key.getKey());
    }

    @Test
    public void keyFilePresent() {
        Path p = Paths.get("key.txt");

        assertTrue(Files.exists(p));
    }

    @Test
    public void keyLengthTest() {
        System.out.println(Key.getKey().length());
        assertTrue(Key.getKey().length() > 0);
    }
}
