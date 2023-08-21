package com.example.Job.Offer.Analyst;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ChromeDriverSingleton {
    private static ChromeDriverSingleton instance;
    private WebDriver driver;

    private ChromeOptions changeChromeOption() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--headless");
        return options;
    }

    private ChromeDriverSingleton() {
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        driver = new ChromeDriver(changeChromeOption());
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }

    public static ChromeDriverSingleton getInstance() {
        if (instance == null) {
            instance = new ChromeDriverSingleton();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        driver.quit();
        instance = null;
    }
}

