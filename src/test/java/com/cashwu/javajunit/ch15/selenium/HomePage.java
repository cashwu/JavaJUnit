package com.cashwu.javajunit.ch15.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author cash.wu
 * @since 2024/06/27
 */
public class HomePage {

    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage openFormAuthentication() {
        webDriver.get("https://the-internet.herokuapp.com");
        webDriver.findElement(By.cssSelector("[href=\"/login\"]")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new LoginPage(webDriver);
    }
}
