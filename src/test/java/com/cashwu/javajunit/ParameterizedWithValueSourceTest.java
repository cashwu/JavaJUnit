package com.cashwu.javajunit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author cash.wu
 * @since 2024/06/06
 */
public class ParameterizedWithValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void test(int value) {
        System.out.println("parameter : " + value);
    }
}
