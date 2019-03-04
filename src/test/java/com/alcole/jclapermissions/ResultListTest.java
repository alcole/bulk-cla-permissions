package com.alcole.jclapermissions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResultListTest {

    private ResultList results;

    @Before
    public void initTestCase() {
        results = new ResultList();
    }


    @Test
    public void constructorTest() {
        assertNotNull(results);
    }

    @Test
    public void addResultTest() {
        assertTrue(results.add(new PermissionResult("title", "00000000", "print", "self", "UK", "True", "True", "True")));
    }

}
