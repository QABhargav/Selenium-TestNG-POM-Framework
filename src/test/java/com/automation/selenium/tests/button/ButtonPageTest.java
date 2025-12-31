package com.automation.selenium.tests.button;

import com.automation.selenium.annotations.Author;
import com.automation.selenium.core.BaseTest;
import com.automation.selenium.core.Retry;
import com.automation.selenium.pages.ButtonPage;
import com.automation.selenium.reporting.ExtentLogger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;

import java.time.Duration;

public class ButtonPageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void tempSetUp(){
        open("/button");
    }

    @Test(groups = {"Smoke", "Regression"})
    @Author("Jay")
    public void shouldNavigateToHomePageOnHomeButtonClick() {
        ButtonPage buttonPage = new ButtonPage(getDriver());
        ExtentLogger.info("User clicks on Home button");
        buttonPage.clickHomeButton();
        ExtentLogger.info("System should navigate user to Home page");
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://letcode.in/", "Incorrect URL after clicking Home button");
        ExtentLogger.pass("User successfully navigated to Home page");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Jay")
    public void shouldFetchXAndYPositionOfButton() {
        ButtonPage buttonPage = new ButtonPage(getDriver());
        ExtentLogger.info("User checks X and Y position of button");
        Point location = buttonPage.positionButton.getLocation();
        ExtentLogger.info("System should return valid X and Y coordinates");
        Assert.assertTrue(location.getX() >= 0 && location.getY() >= 0, "Invalid button coordinates");
        ExtentLogger.pass("Button position fetched successfully");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Jay")
    public void shouldVerifyButtonBackgroundColor() {
        ButtonPage buttonPage = new ButtonPage(getDriver());
        ExtentLogger.info("User checks background color of button");
        String cssColor = buttonPage.getColorButtonCss();
        String actualHex = Color.fromString(cssColor).asHex();
        ExtentLogger.info("System should display correct background color");
        Assert.assertEquals(actualHex, "#2a9d90", "Button background color mismatch");
        ExtentLogger.pass("Button background color verified successfully");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Jay")
    public void shouldVerifyButtonDimensions() {
        ButtonPage buttonPage = new ButtonPage(getDriver());
        ExtentLogger.info("User checks width and height of button");
        Dimension size = buttonPage.propertyButton.getSize();
        ExtentLogger.info("System should display correct button dimensions");
        Assert.assertEquals(size.getWidth(), 174, "Button width mismatch");
        Assert.assertEquals(size.getHeight(), 40, "Button height mismatch");
        ExtentLogger.pass("Button dimensions verified successfully");
    }

    @Test(groups = {"Sanity", "Regression"},enabled = false)
    @Author("Jay")
    public void shouldVerifyDisabledButtonState() {
        ButtonPage buttonPage = new ButtonPage(getDriver());
        ExtentLogger.info("User checks disabled button visibility and state");
        Assert.assertTrue(buttonPage.isDisabledButtonDisplayed(), "Disabled button is not displayed");
        Assert.assertFalse(buttonPage.isDisabledButtonEnabled(), "Disabled button is enabled");
        ExtentLogger.pass("Disabled button state verified successfully");
    }

    @Test(groups = {"Smoke", "Regression"}, retryAnalyzer = Retry.class)
    @Author("Jay")
    public void shouldPerformLongPressOnButton() throws InterruptedException {
        ButtonPage buttonPage = new ButtonPage(getDriver());
        Actions actions = new Actions(getDriver());
        WebElement buttonPress = buttonPage.pressNdHoldBtn;
        ExtentLogger.info("User performs long press on button");
        Assert.assertEquals(buttonPress.getText(), "Button Hold!", "Initial button text mismatch");
        actions.clickAndHold(buttonPress).pause(Duration.ofSeconds(3)).release().perform();
        ExtentLogger.info("System should update text after long press");
        Assert.assertEquals(buttonPress.getText(), "Button has been long pressed", "Text after long press mismatch");
        ExtentLogger.pass("Long press action verified successfully");
    }

}
