package com.automation.selenium.tests.select;

import com.automation.selenium.annotations.Author;
import com.automation.selenium.core.BaseTest;
import com.automation.selenium.pages.SelectPage;
import com.automation.selenium.reporting.ExtentLogger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SelectPageTest extends BaseTest {
    //Dropdown Page
//
    @BeforeMethod(alwaysRun = true)
    public void tempSetUp(){
        open("/dropdowns");
    }

    @Test(groups = {"Smoke", "Regression"})
    @Author("Bhargav")
    public void shouldSelectFruitFromDropdown() {
        SelectPage selectPage = new SelectPage(getDriver());
        ExtentLogger.info("User selects a fruit from dropdown");
        selectPage.selectFruit("Mango");
        ExtentLogger.info("System should display selected fruit confirmation");
        Assert.assertEquals(selectPage.checkSelectedText(), "You have selected Mango", "Selected fruit message mismatch");
        ExtentLogger.pass("Fruit selection verified successfully");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Bhargav")
    public void shouldSelectAndDeselectMultipleSuperheroes() {
        SelectPage selectPage = new SelectPage(getDriver());
        ExtentLogger.info("User verifies superheroes dropdown supports multiple selection");
        Assert.assertTrue(selectPage.isSuperheroMultiSelect(), "Superheroes dropdown does not support multiple selection");
        ExtentLogger.info("User selects multiple superheroes");
        selectPage.selectSuperheroes("Thor", "Superman", "Spider-Man");
        List<String> selected = selectPage.getSelectedSuperheroes();
        ExtentLogger.info("System should display all selected superheroes");
        Assert.assertEquals(selected.size(), 3, "Incorrect number of superheroes selected");
        Assert.assertTrue(selected.contains("Thor"));
        Assert.assertTrue(selected.contains("Superman"));
        Assert.assertTrue(selected.contains("Spider-Man"));
        ExtentLogger.info("User deselects all superheroes");
        selectPage.deselectAllSuperheroes();
        Assert.assertTrue(selectPage.getSelectedSuperheroes().isEmpty(), "All superheroes should be deselected");
        ExtentLogger.pass("Multiple selection and deselection verified successfully");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Bhargav")
    public void shouldSelectLanguageByIndex() {
        SelectPage selectPage = new SelectPage(getDriver());
        ExtentLogger.info("User selects programming language by index");
        selectPage.selectByIndex(2);
        ExtentLogger.info("System should display selected language confirmation");
        Assert.assertEquals(selectPage.checkSelectedText(), "You have selected Python", "Selected language mismatch");
        ExtentLogger.pass("Language selected by index verified successfully");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Bhargav")
    public void shouldVerifyAllCountryOptions() {
        SelectPage selectPage = new SelectPage(getDriver());
        ExtentLogger.info("User retrieves all country options from dropdown");
        List<String> countries = selectPage.getAllCountryOptions();
        ExtentLogger.info("System should display all expected country options");
        List<String> expectedCountries = List.of("Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador", "India", "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela");
        Assert.assertTrue(countries.containsAll(expectedCountries), "Not all expected countries are present");
        ExtentLogger.pass("All country options verified successfully");
    }

    @Test(groups = {"Sanity", "Regression"})
    @Author("Bhargav")
    public void shouldSelectLanguageByValue() {
        SelectPage selectPage = new SelectPage(getDriver());
        ExtentLogger.info("User selects programming language by value");
        selectPage.selectByValue("java");
        ExtentLogger.info("System should display selected language confirmation");
        Assert.assertEquals(selectPage.checkSelectedText(), "You have selected Java", "Selected language mismatch");
        ExtentLogger.pass("Language selected by value verified successfully");
    }


}
