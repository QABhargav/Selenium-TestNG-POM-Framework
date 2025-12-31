package com.automation.selenium.pages;

import com.automation.selenium.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ButtonPage {

    public final WebDriver driver;

    // Constructor
    public ButtonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);         // Initialize PageFactory
    }


    // Selectors
    private By homeButton = By.id("home");
//    private By positionButton = By.id("position");
//    private By colorButton = By.id("color");
//    public By propertyButton = By.id("property");
    private By disabledButton = By.id("isDisabled");

    @FindBy(xpath = "(//button[@id='isDisabled'])[2]")
    public WebElement pressNdHoldBtn;

    @FindBy(id = "position")
    public WebElement positionButton;

    @FindBy(id = "property")
    public WebElement propertyButton;

    @FindBy(id = "color")
    private WebElement colorButton;

    // Actions
    public void clickHomeButton() {
        driver.findElement(homeButton).click();
    }


    public String getColorButtonCss() {
        return colorButton.getCssValue("background-color");
    }

//    public void getPropertyButtonLocation() {
//        driver.findElement(propertyButton).getLocation();
//    }

    public boolean isDisabledButtonDisplayed() {
        return driver.findElement(disabledButton).isDisplayed();
    }

    public boolean isDisabledButtonEnabled() {
        return !driver.findElement(disabledButton).isEnabled();
    }


}
