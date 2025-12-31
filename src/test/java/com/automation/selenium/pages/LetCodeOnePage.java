package com.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LetCodeOnePage {

    public final WebDriver driver;

    public LetCodeOnePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Selectors
    private By fullNameInput = By.id("fullName");
    private By joinInput = By.id("join");
    private By getMeInput = By.id("getMe");
    private By clearMeInput = By.id("clearMe");
    private By noEditInput = By.id("noEdit");
    private By dontWriteInput = By.id("dontwrite");

    // Actions
    public void enterFullName(String name) {
        driver.findElement(fullNameInput).sendKeys(name);
    }

    public void appendText(String text) {
        driver.findElement(joinInput).sendKeys(text);
    }

    public String getTextFromGetMe() {
        return driver.findElement(getMeInput).getAttribute("value");
    }

    public void clearText() {
        driver.findElement(clearMeInput).clear();
    }

    public String getNoEditValue() {
        return driver.findElement(noEditInput).getAttribute("value");
    }

    public String getDontWriteValue() {
        return driver.findElement(dontWriteInput).getAttribute("value");
    }

}
