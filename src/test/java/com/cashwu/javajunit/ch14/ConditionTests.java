package com.cashwu.javajunit.ch14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author cash.wu
 * @since 2024/06/25
 */
@ExtendWith(ExecutionContextExtension.class)
public class ConditionTests {

    @Test
    void conditionDisable() {
        Assertions.assertEquals("aa", "11");
    }
}
