package com.automation.selenium.tests.input;

import com.automation.selenium.annotations.Author;
import com.automation.selenium.core.BaseTest;
import com.automation.selenium.pages.LetCodeOnePage;
import com.automation.selenium.reporting.ExtentLogger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InputPageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void tempSetUp(){
        open("/edit");
    }

    @Test(groups = {"Smoke", "Regression"})
    @Author("Bhargav")
    public void shouldEnterFullName() {

        ExtentLogger.info("User enters full name into Full Name field");
        LetCodeOnePage inputPage = new LetCodeOnePage(getDriver());
        inputPage.enterFullName("John Doe");
        String actualValue = getDriver()
                .findElement(By.id("fullName"))
                .getAttribute("value");
        ExtentLogger.info("System should display entered full name");
        Assert.assertEquals(actualValue, "John Doe");
        ExtentLogger.pass("Full name entered successfully");
    }

    @Test(groups = {"Smoke", "Regression"})
    @Author("Bhargav")
    public void shouldAppendTextToInputField() {

        ExtentLogger.info("User appends text to existing input value");
        LetCodeOnePage inputPage = new LetCodeOnePage(getDriver());
        inputPage.appendText(" - Appended text");

        String actualValue = getDriver()
                .findElement(By.id("join"))
                .getAttribute("value");

        ExtentLogger.info("System should retain existing text and append new text");
        Assert.assertEquals(actualValue, "I am good - Appended text");
        ExtentLogger.pass("Text appended successfully");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Bhargav")
    public void shouldGetTextFromDisabledField() {
        ExtentLogger.info("User reads value from disabled input field");
        LetCodeOnePage inputPage = new LetCodeOnePage(getDriver());
        String actualText = inputPage.getTextFromGetMe();
        ExtentLogger.info("System should return value from disabled field");
        Assert.assertEquals(actualText, "ortonikc");
        ExtentLogger.pass("Value fetched from disabled field successfully");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Bhargav")
    public void shouldClearInputField() {
        ExtentLogger.info("User clears the text from input field");
        LetCodeOnePage inputPage = new LetCodeOnePage(getDriver());
        inputPage.clearText();
        String actualValue = getDriver()
                .findElement(By.id("clearMe"))
                .getAttribute("value");
        ExtentLogger.info("System should remove all text from input field");
        Assert.assertEquals(actualValue, "");
        ExtentLogger.pass("Input field cleared successfully");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Bhargav")
    public void shouldVerifyNonEditableField() {
        ExtentLogger.info("User checks value of non-editable input field");
        LetCodeOnePage inputPage = new LetCodeOnePage(getDriver());
        String actualValue = inputPage.getNoEditValue();
        ExtentLogger.info("System should not allow any value in non-editable field");
        Assert.assertEquals(actualValue, "");
        ExtentLogger.pass("Non-editable field validated successfully");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Bhargav")
    public void shouldVerifyReadOnlyFieldValue() {
        ExtentLogger.info("User checks value of read-only input field");
        LetCodeOnePage inputPage = new LetCodeOnePage(getDriver());
        String actualValue = inputPage.getDontWriteValue();
        ExtentLogger.info("System should display predefined read-only value");
        Assert.assertEquals(actualValue, "This text is readonly");
        ExtentLogger.pass("Read-only field value validated successfully");
    }

}
