package com.saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    WebDriver driver;
    
    @FindBy(id = "shopping_cart_container")
    WebElement cart;
    
    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement postalCode;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(className = "complete-header")
    WebElement completeHeader;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCheckoutButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
       
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void enterUserInformation(String fName, String lName, String zip) {
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        postalCode.sendKeys(zip);
    }
    

    public void clickonCart() {
        cart.click();
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void clickFinishButton() {
        finishButton.click();
    }

    public boolean isOrderComplete() {
        return completeHeader.getText().equalsIgnoreCase("THANK YOU FOR YOUR ORDER!");
    }
}
