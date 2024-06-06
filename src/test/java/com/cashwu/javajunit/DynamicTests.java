package com.cashwu.javajunit;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Iterator;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * @author cash.wu
 * @since 2024/06/06
 */
public class DynamicTests {

    private PositiveNumberPredicate predicate = new PositiveNumberPredicate();

    @TestFactory
    Iterator<DynamicTest> test() {

        return asList(dynamicTest("--", () -> assertFalse(predicate.check(-1))),
                      dynamicTest("00", () -> assertFalse(predicate.check(0))),
                      dynamicTest("++", () -> assertTrue(predicate.check(1)))).iterator();

    }

    private class PositiveNumberPredicate {
        public boolean check(int num) {
            return num > 0;
        }
    }
}
