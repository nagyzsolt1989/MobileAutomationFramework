package tests.android;

import framework.platform.AndroidBaseTest;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import framework.core.BaseTest;
import pages.PrivacyPage;
import org.testng.Assert;
import pages.HomePage;

@Epic("Home")
@Feature("Layout")
public class HomeTests extends AndroidBaseTest {

    @Test
    @Description("Verify the layout of the Home page")
    public void verifyHomePage() {
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
