package com.saucedemo.test;

import com.aventstack.extentreports.Status;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utilities.ScreenshotUtils;

import org.testng.Assert;
import org.testng.annotations.Test;
// Implemented add to cart test to validate the functionality
public class AddToCartTest extends BaseTest {

    @Test
    public void addItemToCartTest() {
        
        test = extent.createTest("Add Item to Cart Test", "Test to validate adding an item to the cart");

        
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "ItemAddedToCart");

        try {
        
            test.log(Status.INFO, "Logging in with standard user credentials.");
            loginPage.login("standard_user", "secret_sauce");

        
            test.log(Status.INFO, "Adding item to the cart");
            inventoryPage.addToCartBackpack();

        
            String cartItemCount = inventoryPage.getCartItemCount();
            Assert.assertEquals(cartItemCount, "1", "Item was not added to the cart!");
            test.addScreenCaptureFromPath(screenshotPath); 
            test.log(Status.PASS, "Item successfully added to the cart");
        } catch (AssertionError ae) {
            
            test.log(Status.FAIL, "Assertion failed: " + ae.getMessage());
            test.addScreenCaptureFromPath(screenshotPath); 
            Assert.fail("Test failed due to an assertion error: " + ae.getMessage());
        } catch (Exception e) {
            
            test.log(Status.FAIL, "Add to cart test is failing with error: " + e.getMessage());
            test.addScreenCaptureFromPath(screenshotPath); 
            Assert.fail("Test failed due to an unexpected exception: " + e.getMessage());
        }
    }
}
