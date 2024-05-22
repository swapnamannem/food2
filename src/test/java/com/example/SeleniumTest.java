package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless"); // Ensure Firefox runs in headless mode
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, 10); // 10 seconds timeout
    }

    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com");
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchBox.sendKeys("Selenium");
        searchBox.submit();
        wait.until(ExpectedConditions.titleContains("Selenium"));
        Assert.assertTrue(driver.getTitle().contains("Selenium"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
