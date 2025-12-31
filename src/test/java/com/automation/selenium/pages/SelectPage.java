package com.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectPage {

    public final WebDriver driver;
    private String loginUrl;

    public SelectPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(id = "fruits")
    private WebElement fruitsDropdown;

    @FindBy(id = "superheros")
    public WebElement superherosDropdown;

    @FindBy(id = "lang")
    private WebElement langDropdown;

    @FindBy(id = "country")
    private WebElement countryDropdown;

    public void selectFruit(String fruit) {
        Select select = new Select(fruitsDropdown);
        select.selectByVisibleText(fruit);
    }

    @FindBy(xpath = "//p[@class='subtitle']")
    private WebElement selectedText;

    public String checkSelectedText(){
        return selectedText.getText();
    }

//***************************************************************************************************

    public boolean isSuperheroMultiSelect() {
        return new Select(superherosDropdown).isMultiple();
    }

    public void selectSuperheroes(String... heroes) {
        Select select = new Select(superherosDropdown);
        for (String hero : heroes) {
            select.selectByVisibleText(hero);
        }
    }

    public List<String> getSelectedSuperheroes() {
        Select select = new Select(superherosDropdown);
        return select.getAllSelectedOptions()
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public void deselectAllSuperheroes() {
        new Select(superherosDropdown).deselectAll();
    }


    public void selectByIndex(int index){
        Select select = new Select(langDropdown);
        select.selectByIndex(index);
    }

    public List<String> getAllCountryOptions(){
        Select select = new Select(countryDropdown);
        return select.getOptions().stream().map(WebElement::getText).toList();
    }

    public void selectByValue(String value){
        Select select = new Select(langDropdown);
        select.selectByValue(value);
    }

}
