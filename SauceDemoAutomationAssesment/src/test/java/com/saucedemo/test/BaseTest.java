package com.saucedemo.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.saucedemo.utilities.ExtentManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeClass
    public void setupSuite() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void teardownSuite() {
        if (extent != null) {
            extent.flush();
        }
    }
}
