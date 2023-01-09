package tests.android;

import framework.platform.AndroidBaseTest;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;
import framework.core.BaseTest;
import pages.PrivacyPage;
import org.testng.Assert;
import pages.HomePage;

public class LoginTests extends AndroidBaseTest {

    @Test
    public void skipLogin() {
        AppiumDriver driver = BaseTest.getDriver();

        PrivacyPage privacyPage = new PrivacyPage(driver);
        HomePage homePage = new HomePage(driver);

        privacyPage.acceptAllCookies();

        Assert.assertTrue(homePage.isMenuButtonDisplayed(), "Menu button was not visible");
        Assert.assertTrue(homePage.isSearchButtonDisplayed(), "Search button was not visible");
        Assert.assertTrue(homePage.isNotificationsButtonDisplayed(), "Notifications button was not visible");
        Assert.assertTrue(homePage.isProfileButtonDisplayed(), "Profile button was not visible");
        Assert.assertTrue(homePage.is9GagLabelDisplayed(), "9Gag label was not visible");
    }
}
