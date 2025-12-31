package com.automation.selenium.tests;

import com.automation.selenium.core.BaseTest;
import com.automation.selenium.pages.LoginPage;
import com.automation.selenium.reporting.ExtentLogger;
import com.automation.selenium.utils.Utilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(enabled = false)
    public void exampleTestCaseFirst() throws InterruptedException {
        boolean abc = true;
        Assert.assertTrue(abc);
        ExtentLogger.fail("This is an example of a failed test case");
    }

    @Test(enabled = false)
    public void exampleTestCaseSecond() throws InterruptedException {
        boolean abc = true;
        Assert.assertTrue(abc);
    }

}
