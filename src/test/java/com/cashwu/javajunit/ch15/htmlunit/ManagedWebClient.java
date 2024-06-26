package com.cashwu.javajunit.ch15.htmlunit;

import org.htmlunit.WebClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author cash.wu
 * @since 2024/06/26
 */
public abstract class ManagedWebClient {

    protected WebClient webClient;

    @BeforeEach
    void setUp() {
        webClient = new WebClient();
    }

    @AfterEach
    void tearDown() {
        webClient.close();
    }
}
