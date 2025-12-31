package com.automation.selenium.reporting;

import com.automation.selenium.core.Listeners;
import com.aventstack.extentreports.Status;

public class ExtentLogger {

    private ExtentLogger() {}

    public static void info(String message) {
        ExtentManager.getTest().info(message);
    }

    public static void pass(String message) {
        ExtentManager.getTest().pass(message);
    }

    public static void fail(String message) {
        ExtentManager.getTest().fail(message);
    }

}
