package com.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AlertPage {

    public final WebDriver driver;

    public AlertPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }




    private By simpleAlertButton = By.id("accept");
    private By confirmAlertButton = By.id("confirm");
    private By promptAlertButton = By.id("prompt");
    private By modernAlertButton = By.id("modern");
    private By modalCloseButton = By.cssSelector(".modal-close");




    // Actions
    public void clickSimpleAlert() {
        driver.findElement(simpleAlertButton).click();
    }

    public void clickConfirmAlert() {
        driver.findElement(confirmAlertButton).click();
    }

    public void clickPromptAlert() {
        driver.findElement(promptAlertButton).click();
    }

    public void clickModernAlert() {
        driver.findElement(modernAlertButton).click();
    }

    public void closeModal() {
        driver.findElement(modalCloseButton).click();
    }



}
