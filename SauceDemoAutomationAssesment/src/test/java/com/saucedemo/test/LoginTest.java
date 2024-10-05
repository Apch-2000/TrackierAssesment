package com.saucedemo.test;

import com.aventstack.extentreports.Status;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utilities.ScreenshotUtils;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// Implemented data driven testing to validate login functionality

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce", true},
                {"locked_out_user", "secret_sauce", false},
                {"invalid_user", "secret_sauce", false},
                {"standard_user", "wrong_password", false},
                {"", "", false}
        };
    }

    @Test(dataProvider = "loginCredentials")
    public void loginTest(String username, String password, boolean isSuccessExpected) {

        if (extent == null) {
            throw new IllegalStateException("ExtentReports instance is null.");
        }
        
        test = extent.createTest("Login Test with Data: " + username, "Testing login with different credentials");
        test.log(Status.INFO, "Performing login for user: " + username);
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        
        try {
            if (isSuccessExpected) {
                String currentUrl = driver.getCurrentUrl();
                Assert.assertTrue(currentUrl.contains("inventory.html"), "Login failed for valid credentials");
                String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "LoginSuccess_" + username);
                test.addScreenCaptureFromPath(screenshotPath);
                test.log(Status.PASS, "Login successful for: " + username);
            } else {
                String errorMessage = loginPage.getErrorMessage();
                Assert.assertTrue(errorMessage.contains("Epic sadface"), "Error message was not displayed for invalid credentials");
                String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "LoginFailed_" + username);
                test.addScreenCaptureFromPath(screenshotPath);
                test.log(Status.PASS, "Login failed as expected for: " + username);
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Login test failed for user: " + username + " Error: " + e.getMessage());
        }
    }
}
