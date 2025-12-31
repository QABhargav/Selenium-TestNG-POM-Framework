package com.automation.selenium.tests.alert;

import com.automation.selenium.annotations.Author;
import com.automation.selenium.core.BaseTest;
import com.automation.selenium.core.Retry;
import com.automation.selenium.pages.AlertPage;
import com.automation.selenium.reporting.ExtentLogger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertPageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void tempSetUp(){
        open("/alert");
    }

    @Test(groups = {"Smoke", "Regression"})
    @Author("Bhargav")
    public void shouldHandleSimpleAlert() throws InterruptedException {
        AlertPage alertPage = new AlertPage(getDriver());
        ExtentLogger.info("User triggers simple alert");
        alertPage.clickSimpleAlert();
        Alert alert = getDriver().switchTo().alert();
        ExtentLogger.info("System should display correct simple alert message");
        Assert.assertEquals(alert.getText(), "Hey! Welcome to LetCode", "Simple alert text mismatch");
        alert.accept();
        ExtentLogger.pass("Simple alert handled successfully");
    }

    @Test(groups = {"Smoke", "Regression"})
    @Author("Bhargav")
    public void shouldHandleConfirmAlert() throws InterruptedException {
        AlertPage alertPage = new AlertPage(getDriver());
        ExtentLogger.info("User triggers confirmation alert");
        alertPage.clickConfirmAlert();
        Alert alert = getDriver().switchTo().alert();
        ExtentLogger.info("System should display correct confirmation alert message");
        Assert.assertEquals(alert.getText(), "Are you happy with LetCode?", "Confirmation alert text mismatch");
        alert.dismiss();
        ExtentLogger.pass("Confirmation alert handled successfully");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Bhargav")
    public void shouldHandlePromptAlert() {
        AlertPage alertPage = new AlertPage(getDriver());
        ExtentLogger.info("User triggers prompt alert and enters input");
        alertPage.clickPromptAlert();
        Alert alert = getDriver().switchTo().alert();
        alert.sendKeys("Test User");
        alert.accept();
        ExtentLogger.pass("Prompt alert handled successfully with user input");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Bhargav")
    public void shouldHandleModernAlertModal() {
        AlertPage alertPage = new AlertPage(getDriver());
        ExtentLogger.info("User triggers modern alert modal");
        alertPage.clickModernAlert();
        ExtentLogger.info("System should display correct modern alert message");
        String modalText = getDriver().findElement(By.cssSelector(".modal-content .title")).getText();
        Assert.assertEquals(modalText, "Modern Alert - Some people address me as sweet alert as well", "Modern alert text mismatch");
        alertPage.closeModal();
        ExtentLogger.pass("Modern alert modal verified and closed successfully");
    }

}