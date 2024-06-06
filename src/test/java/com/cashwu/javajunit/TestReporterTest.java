package com.cashwu.javajunit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.HashMap;

/**
 V* @author cash.wu
 * @since 2024/06/06
 */
public class TestReporterTest {

    @Test
    void testReportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("test entry");
    }

    @Test
    void testReportKeyValuePair(TestReporter testReporter) {
        testReporter.publishEntry("key", "value");
    }

    @Test
    void testReportMultipleKeyValuePair(TestReporter testReporter) {
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        testReporter.publishEntry(map);
    }




}

