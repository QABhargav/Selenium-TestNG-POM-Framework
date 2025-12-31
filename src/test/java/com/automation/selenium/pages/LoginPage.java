package com.automation.selenium.pages;

import com.automation.selenium.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginPage {

    public final WebDriver driver;
    private String loginUrl;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);         // Initialize PageFactory
        this.loginUrl = Utilities.get("loginUrl");
    }

    public LoginPage goTo() {
        driver.get(loginUrl);
        return this;
    }

}
