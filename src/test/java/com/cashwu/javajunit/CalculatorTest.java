package com.cashwu.javajunit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author cash.wu
 * @since 2024/06/04
 */
public class CalculatorTest {

    @Test
    public void testAdd() {

        Calculator calculator = new Calculator();
        double result = calculator.add(1, 2);

        assertEquals(3, result, 0);
    }
}
