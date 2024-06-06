package com.cashwu.javajunit;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author cash.wu
 * @since 2024/06/06
 */
public class RepeatedTests {

    private static Set<Integer> integerSet = new HashSet<>();
    private static List<Integer> integerList = new ArrayList<>();

    @RepeatedTest(value = 5,
                  name = RepeatedTest.LONG_DISPLAY_NAME)
    @DisplayName("test add operation")
    void addNumber() {

        Calculator calculator = new Calculator();

        assertEquals(2, calculator.add(1, 1), "1 +1 = 2");

    }

    @RepeatedTest(value = 5,
                  name = RepeatedTest.LONG_DISPLAY_NAME)
    void addNumberToCollection(TestReporter testReporter,
                               RepetitionInfo repetitionInfo) {

        integerSet.add(1);
        integerList.add(repetitionInfo.getCurrentRepetition());

        testReporter.publishEntry("repetition number",
                                  String.valueOf(repetitionInfo.getCurrentRepetition()));

        System.out.println(("repetition number :: " + repetitionInfo.getCurrentRepetition()));

        assertEquals(1, integerSet.size());
        assertEquals(repetitionInfo.getCurrentRepetition(), integerList.size());
    }

}
