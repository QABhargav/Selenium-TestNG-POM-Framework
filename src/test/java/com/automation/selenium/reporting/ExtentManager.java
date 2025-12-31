package com.automation.selenium.reporting;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

    private static final ThreadLocal<ExtentTest> EXTENT_TEST = new ThreadLocal<>();

    private ExtentManager() {}

    public static ExtentTest getTest() {
        return EXTENT_TEST.get();
    }

    public static void setTest(ExtentTest test) {
        EXTENT_TEST.set(test);
    }

    public static void removeTest() {
        EXTENT_TEST.remove();
    }

}
