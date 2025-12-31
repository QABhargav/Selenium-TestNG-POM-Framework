package com.automation.selenium.core;

import com.automation.selenium.reporting.ExtentManager;
import com.automation.selenium.reporting.ExtentReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {

    private static ExtentReports extent = ExtentReport.getReportObject();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest(
                result.getMethod().getMethodName()
        );

        test.assignCategory(result.getMethod().getGroups());

        com.automation.selenium.annotations.Author author =
                result.getMethod()
                        .getConstructorOrMethod()
                        .getMethod()
                        .getAnnotation(
                                com.automation.selenium.annotations.Author.class
                        );

        if (author != null) {
            test.assignAuthor(author.value());
        }

        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test Passed");
        ExtentManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentManager.getTest().fail(result.getThrowable());

        Object testClass = result.getInstance();
        try {
            String screenshotPath =
                    ((BaseTest) testClass)
                            .getScreenshot(result.getMethod().getMethodName());

            ExtentManager.getTest().addScreenCaptureFromPath(
                    screenshotPath,
                    result.getMethod().getMethodName()
            );

        } catch (IOException e) {
            ExtentManager.getTest().log(Status.WARNING, "Screenshot capture failed");
        } finally {
            ExtentManager.removeTest();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().log(Status.SKIP, "Test Skipped");
        ExtentManager.removeTest();
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public static ExtentTest getTest() {
        return ExtentManager.getTest();
    }
}
