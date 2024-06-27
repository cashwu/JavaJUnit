package com.cashwu.javajunit.ch15.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

/**
 * @author cash.wu
 * @since 2024/06/27
 */
public class LoginPage {
    private final WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage loginWith(String username,
                               String password) {
        webDriver.findElement(id("username")).sendKeys(username);
        webDriver.findElement(id("password")).sendKeys(password);
        webDriver.findElement(cssSelector("#login button")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public void thenLoginSuccessful() {
        assertThat(webDriver.findElement(cssSelector("#flash.success")).isDisplayed()).isTrue();

        assertThat(webDriver.findElement(cssSelector("[href=\"/logout\"]")).isDisplayed()).isTrue();
    }

    public void thenLoginUnsuccessful() {

        assertThat(webDriver.findElement(id("username")).isDisplayed()).isTrue();
        assertThat(webDriver.findElement(id("password")).isDisplayed()).isTrue();
    }
}
