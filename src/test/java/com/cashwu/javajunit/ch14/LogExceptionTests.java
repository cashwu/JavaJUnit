package com.cashwu.javajunit.ch14;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author cash.wu
 * @since 2024/06/25
 */
@ExtendWith(LogExceptionExtension.class)
public class LogExceptionTests {

    @Test
    void name() {

        Person person = new Person();

        person.doThrow();

        assertEquals("", person.getName());
    }
}
