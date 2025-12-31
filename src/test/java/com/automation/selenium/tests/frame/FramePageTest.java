package com.automation.selenium.tests.frame;

import com.automation.selenium.annotations.Author;
import com.automation.selenium.core.BaseTest;
import com.automation.selenium.core.Retry;
import com.automation.selenium.pages.FramePage;
import com.automation.selenium.reporting.ExtentLogger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FramePageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void tempSetUp(){
        open("/frame");
    }

    @Test(groups = {"Smoke", "Regression"}, retryAnalyzer = Retry.class, enabled = true)
    @Author("Bhargav")
    public void shouldFillDetailsInsideIframe() throws InterruptedException {
        FramePage framePage = new FramePage(getDriver());
        ExtentLogger.info("User switches to iframe");
        framePage.switchToIframe();
        ExtentLogger.info("User enters first name and last name inside iframe");
        framePage.fillDetails("Bhargav", "Gohil");
        ExtentLogger.info("System should display correct submitted details");
        Assert.assertTrue(framePage.checkResult(), "Submitted details are not matching");
        ExtentLogger.pass("Details inside iframe filled and verified successfully");
    }

    @Test(groups = {"Sanity", "Regression"}, retryAnalyzer = Retry.class)
    @Author("Bhargav")
    public void shouldEnterEmailInsideInnerIframe() {
        FramePage framePage = new FramePage(getDriver());
        ExtentLogger.info("User switches to inner iframe");
        framePage.switchToInnerFrame();
        ExtentLogger.info("User enters email address inside inner iframe");
        framePage.enterMail("m@il.com");
        ExtentLogger.pass("Email entered successfully inside inner iframe");
    }

}
