package com.cashwu.javajunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author cash.wu
 * @since 2024/06/06
 */
public class TestInfoTest {

    public TestInfoTest(TestInfo testInfo) {
        assertEquals("TestInfoTest", testInfo.getDisplayName());
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {

        String displayName = testInfo.getDisplayName();

        assertTrue(displayName.equals("display name of method") || displayName.equals(
                "testGetNameOfTheMethod(TestInfo)"));

    }

    @Test
    void testGetNameOfTheMethod(TestInfo testInfo) {

        assertEquals("testGetNameOfTheMethod(TestInfo)", testInfo.getDisplayName());
    }

    @Test
    @DisplayName("display name of method")
    void testGetNameOfTheMethodWithAnnotation(TestInfo testInfo) {

        assertEquals("display name of method", testInfo.getDisplayName());
    }

}
