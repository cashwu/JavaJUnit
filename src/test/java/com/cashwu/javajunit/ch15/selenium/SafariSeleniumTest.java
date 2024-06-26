package com.cashwu.javajunit.ch15.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cash.wu
 * @since 2024/06/26
 */
public class SafariSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new SafariDriver();
    }

    @Test
    void manning() {
        driver.get("https://www.manning.com/");
        assertThat(driver.getTitle()).isEqualTo("Manning");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
