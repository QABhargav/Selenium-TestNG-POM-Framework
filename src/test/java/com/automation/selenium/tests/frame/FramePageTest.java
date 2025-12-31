package com.automation.selenium.tests.frame;

import com.automation.selenium.core.BaseTest;
import com.automation.selenium.pages.FramePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FramePageTest extends BaseTest {

    @BeforeMethod
    public void tempSetUp(){
        open("/frame");
    }

    @Test
    public void testSwitchToIframeAndFillNames() throws InterruptedException {
        FramePage framePage = new FramePage(getDriver());
        // Switch to iframe
        framePage.switchToIframe();
        // Click on Watch tutorial button
        framePage.fillDetails("Bhargav","Gohil");
        Assert.assertTrue(framePage.checkResult(),"not matching text");
    }

    @Test
    public void testIframePresence() {
        FramePage framePage = new FramePage(getDriver());
        framePage.switchToInnerFrame();
        framePage.enterMail("m@il.com");
    }

}
