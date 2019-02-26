package com.alcole.jclapermissions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class IdentifierHelperTest {

    @Test
    public void classifyTest(){
        assertEquals("ISBN", IdentifierHelper.classify("9781231231234"));
        assertEquals("ISBN", IdentifierHelper.classify("978-1-2312-3123-4"));
        assertEquals("ISSN", IdentifierHelper.classify("00014664"));
        assertEquals("ISSN", IdentifierHelper.classify(" 00014664"));

        assertNotEquals("invalid", IdentifierHelper.classify("00014665"));
    }

    @Test
    public void cleanTest() {
        assertEquals("00014664", IdentifierHelper.clean("00014664"));
        assertEquals("00014664", IdentifierHelper.clean(" 00014664"));
        assertEquals("00014664", IdentifierHelper.clean("0001-4664"));
        assertEquals("00014664", IdentifierHelper.clean("0001-4664 "));
    }

}
