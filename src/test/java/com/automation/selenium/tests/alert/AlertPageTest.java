package com.automation.selenium.tests.alert;

import com.automation.selenium.core.BaseTest;
import com.automation.selenium.pages.AlertPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertPageTest extends BaseTest {

    @BeforeMethod
    public void tempSetUp(){
        open("/alert");
    }

    @Test
    public void testSimpleAlert() throws InterruptedException {
        AlertPage alertPage = new AlertPage(getDriver());
        alertPage.clickSimpleAlert();

        Alert alert = getDriver().switchTo().alert();
        String alertText = alert.getText();

        System.out.println(alertText);

        Assert.assertEquals("Hey! Welcome to LetCode", alertText);
        alert.accept();
    }

    @Test
    public void testConfirmAlert() throws InterruptedException {
        AlertPage alertPage = new AlertPage(getDriver());

        alertPage.clickConfirmAlert();
        Alert alert = getDriver().switchTo().alert();

        String alertText = alert.getText();
        System.out.println(alertText);

        Assert.assertEquals("Are you happy with LetCode?", alertText);
        alert.dismiss();
    }

    @Test
    public void testPromptAlert() {
        AlertPage alertPage = new AlertPage(getDriver());

        alertPage.clickPromptAlert();

        Alert alert = getDriver().switchTo().alert();
        alert.sendKeys("Test User");

        String alertText = alert.getText();
        System.out.println(alertText);

        alert.accept();
    }

    @Test
    public void testModernAlert() {
        AlertPage alertPage = new AlertPage(getDriver());

        alertPage.clickModernAlert();
        // Verify the modal content
        String modalText = getDriver().findElement(By.cssSelector(".modal-content .title")).getText();

        System.out.println(modalText);

        Assert.assertEquals("Modern Alert - Some people address me as sweet alert as well", modalText);
        alertPage.closeModal();
    }

}