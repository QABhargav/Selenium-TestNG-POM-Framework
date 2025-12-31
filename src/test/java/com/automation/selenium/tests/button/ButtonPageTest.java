package com.automation.selenium.tests.button;

import com.automation.selenium.core.BaseTest;
import com.automation.selenium.core.Retry;
import com.automation.selenium.pages.ButtonPage;
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

    @BeforeMethod
    public void tempSetUp(){
        open("/button");
    }

    @Test  //Done Test Case -------------------------------------
    public void testHomeButtonNavigation() {
        ButtonPage buttonPage = new ButtonPage(getDriver());
        buttonPage.clickHomeButton();

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "https://letcode.in/",
                "Expected URL after clicking home button"
        );
    }

    @Test  //Done Test Case -------------------------------------
    public void testGetXYLocationFunctionality() {
        Point location = new ButtonPage(getDriver()).positionButton.getLocation();

        int x = (int) location.getX();
        int y = (int) location.getY();

        System.out.println("X: " + x);
        System.out.println("Y: " + y);
    }

    @Test  //Done Test Case -------------------------------------
    public void testColorButtonFunctionality() {
        String colorA = new ButtonPage(getDriver()).getColorButtonCss();
        System.out.println(colorA);

        String actualHex = Color.fromString(colorA).asHex();
        System.out.println(actualHex);
        Assert.assertEquals(actualHex,"#2a9d90","Color button background color mismatch");
    }

    @Test  //Done Test Case -------------------------------------
    public void testPositionButtonFunctionality() {
        ButtonPage buttonPage = new ButtonPage(getDriver());
        Dimension size = buttonPage.propertyButton.getSize();

        System.out.println("Width in pixel: " + size.getWidth());
        System.out.println("Height in pixel: " + size.getHeight());

        Assert.assertEquals(size.getWidth(),174,"width matches");
        Assert.assertEquals(size.getHeight(),40,"height matches");
    }

    @Test
    public void testDisabledButton() {
        ButtonPage buttonPage = new ButtonPage(getDriver());

        Assert.assertTrue(buttonPage.isDisabledButtonDisplayed(),"Disabled button should be displayed");
        Assert.assertFalse(buttonPage.isDisabledButtonEnabled(),"Disabled button should be disabled");
    }

    @Test(retryAnalyzer = Retry.class)
    public void holdTest() throws InterruptedException {
        ButtonPage buttonPage = new ButtonPage(getDriver());
        Actions actions = new Actions(getDriver());

        WebElement buttonPress = buttonPage.pressNdHoldBtn;

        Assert.assertEquals(buttonPress.getText(),"Button Hold!", "Text mismatch");
        System.out.println(buttonPress.getText());

        actions.clickAndHold(buttonPress)
                .pause(Duration.ofSeconds(3))
                .release()
                .perform();

        Assert.assertEquals(buttonPress.getText(),"Button has been long pressed", "Text mismatch");
        System.out.println(buttonPress.getText());
    }

}
