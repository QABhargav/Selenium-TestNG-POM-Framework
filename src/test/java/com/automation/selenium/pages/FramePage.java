package com.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FramePage {

    public final WebDriver driver;

    public FramePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    // Selectors
    private By iframeLocator = By.id("firstFr");
    private By innerFrame = By.xpath("//iframe[@src='innerframe']");
    private By watchTutorialButton = By.xpath("//button[contains(text(),'Watch tutorial')]");

    @FindBy(name = "fname")
    private WebElement firstName;

    @FindBy(name = "lname")
    private WebElement secondName;

    @FindBy(xpath = "//p[text()='You have entered Bhargav Gohil']")
    private WebElement result;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;

    public void fillDetails(String frstName, String scndName){
        firstName.sendKeys(frstName);
        secondName.sendKeys(scndName);
    }

    public boolean checkResult(){
        return result.isDisplayed();
    }

    // Actions
    public void switchToIframe() {
        driver.switchTo().frame(driver.findElement(iframeLocator));
    }

    public void switchToInnerFrame() {
        driver.switchTo().frame(driver.findElement(iframeLocator));
        driver.switchTo().frame(driver.findElement(innerFrame));
    }

    public void enterMail(String mailId){
        emailField.sendKeys(mailId);
    }

    public void clickWatchTutorial() {
        driver.findElement(watchTutorialButton).click();
    }

}
