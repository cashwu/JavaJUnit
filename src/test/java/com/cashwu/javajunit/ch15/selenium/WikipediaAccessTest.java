package com.cashwu.javajunit.ch15.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setMaxLengthForSingleLineDescription;

/**
 * @author cash.wu
 * @since 2024/06/26
 */
public class WikipediaAccessTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new SafariDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void wiki() throws InterruptedException {

        driver.get("https://en.wikipedia.org/");

        assertThat(driver.getTitle()).isEqualTo("Wikipedia, the free encyclopedia");

        WebElement contents = driver.findElement(By.linkText("Create account"));

        assertThat(contents.isDisplayed()).isTrue();

        contents.click();
        Thread.sleep(3000);

        assertThat(driver.getTitle()).isEqualTo("Create account - Wikipedia");

    }
}
