package com.saucedemo.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// Implementing ExtentReports for generating reports
//Follow this path "/test-output/ExtentReport.html" to open
public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);


            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}