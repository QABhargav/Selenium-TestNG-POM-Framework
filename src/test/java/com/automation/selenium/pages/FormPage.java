package com.automation.selenium.pages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FormPage {

    public final WebDriver driver;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Selectors
    private By firstNameInput = By.id("firstname");
    private By lastNameInput = By.id("lasttname");
    private By emailInput = By.id("email");
//    private By countryCodeSelect = By.id("countrycode");
    private By phoneNumberInput = By.id("Phno");
    private By addressLine1Input = By.id("Addl1");
    private By addressLine2Input = By.id("Addl2");
    private By stateInput = By.id("state");
    private By postalCodeInput = By.id("postalcode");
//    private By countrySelect = By.id("country");
    private By dobInput = By.id("Date");
//    private By genderMaleRadio = By.id("male");
//    private By genderFemaleRadio = By.id("female");
//    private By genderTransgenderRadio = By.id("trans");
    private By termsCheckbox = By.xpath("//label[@class='checkbox']/input");
    private By submitButton = By.xpath("//input[@type='submit']");


    // Actions
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }



    @FindBy(xpath = "(//div[contains(@class,'select')]/select)[1]")
    private WebElement countryCodeSelect;
    public void selectCountryCode(String countryCode) {
        Select select = new Select(countryCodeSelect);
        select.selectByValue(countryCode);
    }

    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void enterAddressLine1(String address) {
        driver.findElement(addressLine1Input).sendKeys(address);
    }

    public void enterAddressLine2(String address) {
        driver.findElement(addressLine2Input).sendKeys(address);
    }

    public void enterState(String state) {
        driver.findElement(stateInput).sendKeys(state);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }




//    private By countrySelect = By.id("country");

    @FindBy(xpath = "(//div[contains(@class,'select')]/select)[2]")
    private WebElement countrySelect;

    public void selectCountry(String country) {
        Select select = new Select(countrySelect);
        select.selectByValue(country);
    }

    public void enterDateOfBirth(String dob) {
        driver.findElement(dobInput).sendKeys(dob);
    }





    @FindBy(xpath = "//input[@type='radio' and @id='male']")
    private WebElement genderMaleRadio;

    @FindBy(xpath = "//input[@type='radio' and @id='female']")
    private WebElement genderFemaleRadio;

    @FindBy(xpath = "//input[@type='radio' and @id='trans']")
    private WebElement genderTransgenderRadio;

    public void selectGender(String gender) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", genderMaleRadio);

        if (gender.equalsIgnoreCase("male")) {
            genderMaleRadio.click();
        } else if (gender.equalsIgnoreCase("female")) {
            genderFemaleRadio.click();
        } else if (gender.equalsIgnoreCase("transgender")) {
            genderTransgenderRadio.click();
        }
    }

    public void agreeToTerms() {
        driver.findElement(termsCheckbox).click();
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }


}
