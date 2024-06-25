package com.cashwu.javajunit.ch14;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author cash.wu
 * @since 2024/06/25
 */
@ExtendWith(ObjectParameterResolver.class)
public class ObjectParameterTests {

    private final Person person;

    public ObjectParameterTests(Person person) {
        this.person = person;
    }

    @Test
    void name() {
        String name = person.getName();

        assertEquals("cash", name);
    }
}
