package com.saucedemo.test;

import com.aventstack.extentreports.Status;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utilities.ScreenshotUtils;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

	
	
	// Test to validate complete checkout process
    @Test
    public void validateCheckoutProcess() {
        test = extent.createTest("Checkout Process Test", "Test to validate the entire checkout process");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
                inventoryPage.addToCartBackpack();  

                CheckoutPage checkoutPage = new CheckoutPage(driver);
                String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "CheckoutSuccess");
                try {
                checkoutPage.clickonCart();
                test.log(Status.INFO, "Clicking the checkout button");
                
                checkoutPage.clickCheckoutButton();  // Proceed to checkout
                checkoutPage.enterUserInformation("Apoorav", "Chaudhary", "176057");
                checkoutPage.clickContinueButton();
                checkoutPage.clickFinishButton();
                boolean isOrderComplete = checkoutPage.isOrderComplete();
                Assert.assertTrue(isOrderComplete, "Order was not completed successfully");
                test.addScreenCaptureFromPath(screenshotPath);
                test.log(Status.PASS, "Checkout process completed successfully");
                
               
                
                }	
                catch (Exception e) {
                	test.addScreenCaptureFromPath(screenshotPath);
                	test.log(Status.FAIL, "Checkout test failed with error: " + e.getMessage());

                }
                 

                
               
           
            }
        }

