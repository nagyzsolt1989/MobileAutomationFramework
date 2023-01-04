package tests.android;

import framework.core.BaseTest;
import framework.platform.AndroidBaseTest;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.IntroPage;

public class LoginTests extends AndroidBaseTest {

    @Test
    public void verifyHomePage() {
        AppiumDriver driver = BaseTest.getDriver();

        IntroPage introPage = new IntroPage(driver);
        HomePage homePage = new HomePage(driver);

        introPage.clickOnSkipButton();
        Assert.assertTrue(homePage.isSearchButtonDisplayed(), "Search button was not visible");
    }
}
