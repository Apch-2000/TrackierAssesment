package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {

    WebDriver driver;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement addToCartBackpack;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addToCartBackpack() {
        addToCartBackpack.click();
    }

    public String getCartItemCount() {
        return cartBadge.getText();
    }
}
