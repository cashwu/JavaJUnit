package com.cashwu.javajunit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author cash.wu
 * @since 2024/06/06
 */
public class HamcrestListTest {

    private List<String> values;

    @BeforeEach
    void setUp() {
        values = new ArrayList<>();
        values.add("one");
        values.add("two");
        values.add("three");
    }

    @Test
    @DisplayName("without Hamcrest")
    void withoutHamcrest() {
        assertEquals(3, values.size());

        assertTrue(values.contains("one") || values.contains("two") || values.contains("three"));
    }

    @Test
    @DisplayName("with Hamcrest")
    void withHamcrest() {

        // Hamcrest
        assertThat(values, hasSize(3));
        assertThat(values, hasItem(anyOf(equalTo("one"), equalTo("two"), equalTo("three"))));

        // assertJ
        Assertions.assertThat(values).hasSize(3);
        Assertions.assertThat(values).containsAnyOf("one", "two", "three");
    }
}
