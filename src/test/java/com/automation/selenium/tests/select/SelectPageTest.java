package com.automation.selenium.tests.select;

import com.automation.selenium.core.BaseTest;
import com.automation.selenium.pages.SelectPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class SelectPageTest extends BaseTest {
    //Dropdown Page
//
    @BeforeMethod
    public void tempSetUp(){
        open("/dropdowns");
    }

    //Just for Check 11
    //----
    @Test
    public void testSelectFruit() {
        SelectPage selectPage = new SelectPage(getDriver());
        selectPage.selectFruit("Mango");
        // Assertion to check if the selected fruit is Mango
        Assert.assertEquals(selectPage.checkSelectedText(),"You have selected Mango","Selected fruit message mismatch");
    }

    @Test
    public void testSelectMultipleSuperheroes() {
        SelectPage selectPage = new SelectPage(getDriver());

        Assert.assertTrue(selectPage.isSuperheroMultiSelect(),"Superheroes dropdown should allow multiple selection");

        selectPage.selectSuperheroes("Thor", "Superman", "Spider-Man");

        List<String> selected = selectPage.getSelectedSuperheroes();
        Assert.assertEquals(selected.size(), 3);
        Assert.assertTrue(selected.contains("Thor"));
        Assert.assertTrue(selected.contains("Superman"));
        Assert.assertTrue(selected.contains("Spider-Man"));

        selectPage.deselectAllSuperheroes();

        Assert.assertTrue(selectPage.getSelectedSuperheroes().isEmpty(),"All superheroes should be deselected");
    }


    @Test
    public void testSelectByIndex() throws InterruptedException {
        SelectPage selectPage = new SelectPage(getDriver());
        selectPage.selectByIndex(2);
        Thread.sleep(2000);
        Assert.assertEquals(selectPage.checkSelectedText(),"You have selected Python","Selected Language is mismatch");
    }

    @Test
    public void testGetAllOptions() throws InterruptedException {
        SelectPage selectPage = new SelectPage(getDriver());

        List<String> countries = selectPage.getAllCountryOptions();
        System.out.println(countries);
        Assert.assertTrue(countries.contains("India"));

        List<String> expectedCountries = List.of("Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador", "India", "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela");

        Assert.assertTrue(countries.containsAll(expectedCountries),"Not all expected countries are present");
    }

    @Test
    public void testSelectCountryByValue() {
        SelectPage selectPage = new SelectPage(getDriver());
        selectPage.selectByValue("java");
        Assert.assertEquals(selectPage.checkSelectedText(),"You have selected Java","Selected Language is mismatch");
    }

}
