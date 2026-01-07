package com.automation.selenium.tests.form;

import com.automation.selenium.annotations.Author;
import com.automation.selenium.core.BaseTest;
import com.automation.selenium.core.Retry;
import com.automation.selenium.pages.FormPage;
import com.automation.selenium.reporting.ExtentLogger;
import com.automation.selenium.utils.DataProviderUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormPageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void tempSetUp(){
        open("/forms");
    }

    @Test(groups = {"Critical", "Regression"},retryAnalyzer = Retry.class)  //invocationCount = 5
    @Author("Bhargav")
    public void testFormSubmission() {

        FormPage formPage = new FormPage(getDriver());
        formPage.enterFirstName("John");
        formPage.enterLastName("Doe");
        formPage.enterEmail("john.doe@example.com");
        formPage.selectCountryCode("91");
        formPage.enterPhoneNumber("1234567890");
        formPage.enterAddressLine1("123 Main St");
        formPage.enterAddressLine2("Apt 4B");
        formPage.enterState("NY");
        formPage.enterPostalCode("10001");
        formPage.selectCountry("India");
        formPage.enterDateOfBirth("1990-01-01");
        formPage.selectGender("male");
        formPage.agreeToTerms();
        ExtentLogger.info("User filled form with valid details");
        formPage.submitForm();
        ExtentLogger.info("User submitted the form");

    }

    @Test(groups = {"Sanity", "Regression"},retryAnalyzer = Retry.class)
    @Author("Bhargav")
    public void testEmptyFirstName() {

        FormPage formPage = new FormPage(getDriver());
//        formPage.enterFirstName("John");
        formPage.enterLastName("Doe");
        formPage.enterEmail("john.doe@example.com");
        formPage.selectCountryCode("91");
        formPage.enterPhoneNumber("1234567890");
        formPage.enterAddressLine1("123 Main St");
        formPage.enterAddressLine2("Apt 4B");
        formPage.enterState("NY");
        formPage.enterPostalCode("10001");
        formPage.selectCountry("India");
        formPage.enterDateOfBirth("01-01-1990");
        formPage.selectGender("male");
        formPage.agreeToTerms();
        ExtentLogger.info("User filled form without entering first name");
        formPage.submitForm();
        ExtentLogger.info("User submitted the form");

        WebElement field = getDriver().findElement(By.xpath("//input[@required]"));
        String message = field.getAttribute("validationMessage");
//        Assert.assertEquals(message, "Please fill out this field.");
        Assert.assertEquals(message, "Please fill in this field.");
        ExtentLogger.pass("First name mandatory validation verified successfully");

    }

    @Test(groups = {"Smoke", "Regression"},retryAnalyzer = Retry.class)
    @Author("Bhargav")
    public void testInvalidEmail() {
        FormPage formPage = new FormPage(getDriver());
        formPage.enterFirstName("John");
        formPage.enterLastName("Doe");
        formPage.enterEmail("bhargav@");
        formPage.selectCountryCode("91");
        formPage.enterPhoneNumber("1234567890");
        formPage.enterAddressLine1("123 Main St");
        formPage.enterAddressLine2("Apt 4B");
        formPage.enterState("NY");
        formPage.enterPostalCode("10001");
        formPage.selectCountry("India");
        formPage.enterDateOfBirth("01-01-1990");
        formPage.selectGender("male");
        formPage.agreeToTerms();
        ExtentLogger.info("User entered invalid email format in form");

        formPage.submitForm();
        ExtentLogger.info("User submitted the form");

        WebElement email = getDriver().findElement(By.xpath("//input[@type='email']"));

        String message = email.getAttribute("validationMessage");
//        Assert.assertEquals(message, "Please fill out this field.");
        Assert.assertEquals(message, "Please enter a part following '@'. 'bhargav@' is incomplete.");
        ExtentLogger.pass("Invalid email validation verified successfully");

    }

    @Test(groups = {"Smoke", "Regression"},retryAnalyzer = Retry.class)
    @Author("Bhargav")
    public void testPhoneNumberPattern() {
        FormPage formPage = new FormPage(getDriver());

        formPage.enterFirstName("John");
        formPage.enterLastName("Doe");
        formPage.enterEmail("bhargav@mail.com");
        formPage.selectCountryCode("91");
        formPage.enterPhoneNumber("1234567890ABC");
        formPage.enterAddressLine1("123 Main St");
        formPage.enterAddressLine2("Apt 4B");
        formPage.enterState("NY");
        formPage.enterPostalCode("10001");
        formPage.selectCountry("India");
        formPage.enterDateOfBirth("01-01-1990");
        formPage.selectGender("male");
        formPage.agreeToTerms();
        ExtentLogger.info("User filled form with invalid phone number details");
        formPage.submitForm();
        ExtentLogger.info("User submitted the form");

        WebElement phone = getDriver().findElement(By.id("Phno"));
        String message = phone.getAttribute("validationMessage");
        Assert.assertEquals(message, "Please match the format requested.");
        ExtentLogger.pass("Phone number pattern validation verified successfully");
    }

    @Test(groups = {"Smoke", "Regression"},retryAnalyzer = Retry.class)
    @Author("Bhargav")
    public void testPhoneNumberPattern_DemoFailedTestCase() {
        FormPage formPage = new FormPage(getDriver());

        formPage.enterFirstName("John");
        formPage.enterLastName("Doe");
        formPage.enterEmail("bhargav@mail.com");
        formPage.selectCountryCode("91");
        formPage.enterPhoneNumber("1234567890ABC");
        formPage.enterAddressLine1("123 Main St");
        formPage.enterAddressLine2("Apt 4B");
        formPage.enterState("NY");
        formPage.enterPostalCode("10001");
        formPage.selectCountry("India");
        formPage.enterDateOfBirth("01-01-1990");
        formPage.selectGender("male");
        formPage.agreeToTerms();
        ExtentLogger.info("User filled form with invalid phone number details");
        formPage.submitForm();
        ExtentLogger.info("User submitted the form");

        WebElement phone = getDriver().findElement(By.id("Phno"));
        String message = phone.getAttribute("validationMessage");
        Assert.assertEquals(message, "Please match the format requested.Test");
        ExtentLogger.pass("Phone number pattern validation verified successfully");
    }

//-------------------------------------------------------------------------------------------------------------------------------
// This is the data we're getting from Excel file

    @Test(groups = {"Regression"}, dataProvider = "inputData", dataProviderClass = DataProviderUtils.class)
    @Author("Bhargav")
    public void dataDrivenTestDevelopment(String firstName, String lastName) {
        ExtentLogger.info("In this test data used from excel file");
        FormPage formPage = new FormPage(getDriver());
        formPage.enterFirstName(firstName);
        formPage.enterLastName(lastName);
        ExtentLogger.info("Data used for test :"+firstName +", "+ lastName);
        formPage.enterEmail("john.doe@example.com");
        formPage.selectCountryCode("91");
        formPage.enterPhoneNumber("1234567890");
        formPage.enterAddressLine1("123 Main St");
        formPage.enterAddressLine2("Apt 4B");
        formPage.enterState("NY");
        formPage.enterPostalCode("10001");
        formPage.selectCountry("India");
        formPage.enterDateOfBirth("1990-01-01");
        formPage.selectGender("male");
        formPage.agreeToTerms();
        ExtentLogger.info("User filled form with valid details");
        formPage.submitForm();
        ExtentLogger.info("User submitted the form");
    }

//-------------------------------------------------------------------------------------------------------------------------------
// This is the data we're getting from Json

    @Test(groups = {"Regression"}, dataProvider = "jsonFormData", dataProviderClass = DataProviderUtils.class)
    @Author("Bhargav")
    public void dataDrivenTestDevelopment_JsonData(JsonNode data) {
        ExtentLogger.info("User fills form using JSON test data");
        FormPage formPage = new FormPage(getDriver());
        formPage.enterFirstName(data.get("firstName").asText());
        formPage.enterLastName(data.get("lastName").asText());
        formPage.enterEmail(data.get("email").asText());
        formPage.selectCountryCode(data.get("countryCode").asText());
        formPage.enterPhoneNumber(data.get("phone").asText());
        formPage.enterAddressLine1(data.get("address1").asText());
        formPage.enterAddressLine2(data.get("address2").asText());
        formPage.enterState(data.get("state").asText());
        formPage.enterPostalCode(data.get("postalCode").asText());
        formPage.selectCountry(data.get("country").asText());
        formPage.enterDateOfBirth(data.get("dob").asText());
        formPage.selectGender(data.get("gender").asText());
        formPage.agreeToTerms();
        ExtentLogger.info("User submits the form");
        formPage.submitForm();
        ExtentLogger.pass("Form submitted successfully using JSON data");
    }


}