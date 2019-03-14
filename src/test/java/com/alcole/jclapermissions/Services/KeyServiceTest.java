package com.alcole.jclapermissions.Services;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

public class KeyServiceTest {

  @BeforeClass
  public static void beforeTest() {
    System.out.println("Starting KeyServiceTest");
  }

  @AfterClass
  public static void afterTest() {
    System.out.println("Ending KeyServiceTest");
  }

  @Test
  public void keyNotNullTest() throws IOException {
    assertNotNull(KeyService.getKey());
  }

  @Test
  public void keyFilePresent() {
    Path p = Paths.get("key.txt");
    assertTrue(Files.exists(p));
  }

  @Test
  public void keyLengthTest() throws IOException {
    assertTrue(KeyService.getKey().length() > 0);
  }
}
