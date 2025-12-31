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
 //--Test
    @Test(groups = {"Smoke"})
    @Author("Rahul")
    public void testEnterFullName() throws InterruptedException {

        new LetCodeOnePage(getDriver()).enterFullName("John Doe");
        ExtentLogger.info("Entered Name 'John Doe'");
        Assert.assertEquals("John Doe", getDriver().findElement(By.id("fullName")).getAttribute("value"));
        ExtentLogger.pass("Test Test Passed'");
        Thread.sleep(500);
    }

    @Test(groups = {"Smoke"})
    @Author("Rahul")
    public void testAppendText() throws InterruptedException {
//        open("/edit");

        new LetCodeOnePage(getDriver()).appendText(" - Appended text");
        Assert.assertEquals("I am good - Appended text", getDriver().findElement(By.id("join")).getAttribute("value"));
        Thread.sleep(500);
    }

    @Test(groups = {"Sanity"})
    @Author("Rahul")
    public void testGetTextFromGetMe() throws InterruptedException {
        String text = new LetCodeOnePage(getDriver()).getTextFromGetMe();
        Assert.assertEquals("ortonikc", text);
        Thread.sleep(500);
    }

    @Test(groups = {"Sanity"})
    @Author("Rahul")
    public void testClearText() throws InterruptedException {
        new LetCodeOnePage(getDriver()).clearText();
        Assert.assertEquals("", getDriver().findElement(By.id("clearMe")).getAttribute("value"));
        Thread.sleep(500);
    }

    @Test(groups = {"Sanity"})
    @Author("Rahul")
    public void testNoEditValue() throws InterruptedException {
        String value = new LetCodeOnePage(getDriver()).getNoEditValue();
        Assert.assertEquals("", value);
        Thread.sleep(500);
    }

    @Test(groups = {"Sanity"})
    @Author("Rahul")
    public void testDontWriteValue() throws InterruptedException {
        String value = new LetCodeOnePage(getDriver()).getDontWriteValue();
        Assert.assertEquals("This text is readonly", value);
        Thread.sleep(500);
    }

}
