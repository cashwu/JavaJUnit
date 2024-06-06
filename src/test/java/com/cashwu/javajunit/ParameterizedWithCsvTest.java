package com.cashwu.javajunit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author cash.wu
 * @since 2024/06/06
 */
public class ParameterizedWithCsvTest {

    @ParameterizedTest
    @CsvSource({"1, aa", "2, bb", "3, tt"})
    void testCsv(int index,
                 String name) {

        System.out.println("index: " + index + ", name: " + name);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/tt.csv")
    void testCsvFromFile(int index,
                         String name) {

        System.out.println("index: " + index + ", name: " + name);
    }
}
