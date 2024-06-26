package com.cashwu.javajunit.ch15.selenium;

import org.htmlunit.WebAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cash.wu
 * @since 2024/06/26
 */
public class GoogleSearchTest {

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
    void google() {

        driver.get("https://www.google.com");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("en.wikipedia.org");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        WebElement myDynamicElements = (new WebDriverWait(driver, Duration.ofSeconds(3))).until(
                ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));

        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//a/h3"));

        findElements.get(0).click();

        assertThat(driver.getCurrentUrl()).isEqualTo("https://en.wikipedia.org/wiki/Main_Page");
        assertThat(driver.getTitle()).isEqualTo("Wikipedia, the free encyclopedia");

    }
}
