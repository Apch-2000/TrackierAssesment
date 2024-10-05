package com.saucedemo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

// Implemented Screenshot utilities for Taking screenshots open screenshots folder to view screenshots

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String filePath = "screenshots/" + fileName + ".png";

        try {
            FileUtils.copyFile(source, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath; 
    }
}
