package com.cashwu.javajunit.ch14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author cash.wu
 * @since 2024/06/25
 */
@ExtendWith(OperationExtension.class)
public class OperationTests {

    @Test
    void aa() {
        System.out.println("aa");
        Assertions.assertTrue(true);
    }

    @Test
    void bb() {
        System.out.println("bb");
        Assertions.assertTrue(true);
    }


}
