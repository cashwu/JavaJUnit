package com.cashwu.javajunit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * @author cash.wu
 * @since 2024/06/06
 */
public class ParameterizedWithEnumSourceTest {

    @ParameterizedTest
    @EnumSource(Sentences.class)
    void enumTest(Sentences sentences) {
        System.out.println("val :: " + sentences.Val());
    }

    @ParameterizedTest
    @EnumSource(value = Sentences.class,
                names = {"Test1", "Test3"})
    void enumTestWithName(Sentences sentences) {
        System.out.println("val with name :: " + sentences.Val());
    }

    @ParameterizedTest
    @EnumSource(value = Sentences.class,
                mode = EnumSource.Mode.EXCLUDE,
                names = {"Test2"})
    void enumTestWithNameExclude(Sentences sentences) {
        System.out.println("val with name :: " + sentences.Val());
    }

    enum Sentences {

        Test1(1),
        Test2(2),
        Test3(3);

        private final int val;

        Sentences(int val) {
            this.val = val;
        }

        public int Val() {
            return val;
        }
    }
}
