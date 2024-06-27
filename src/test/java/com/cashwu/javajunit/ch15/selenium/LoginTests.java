package com.cashwu.javajunit.ch15.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * @author cash.wu
 * @since 2024/06/27
 */
public class LoginTests {

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
    void loginWithValidCredentials() {

        HomePage homePage = new HomePage(driver);

        homePage.openFormAuthentication().loginWith("tomsmith", "SuperSecretPassword!")
                .thenLoginSuccessful();
    }

    @Test
    void loginWithInvalidCredentials() {

        HomePage homePage = new HomePage(driver);

        homePage.openFormAuthentication().loginWith("tomsmith", "wrongPassword!")
                .thenLoginUnsuccessful();
    }

}
