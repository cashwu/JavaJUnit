package com.cashwu.javajunit.ch14;

import org.junit.jupiter.api.extension.*;

/**
 * @author cash.wu
 * @since 2024/06/25
 */
public class OperationExtension
        implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("i ame after all");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("i ame after each");
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("i ame before all");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("i ame before each");
    }
}
