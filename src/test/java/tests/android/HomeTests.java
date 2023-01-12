package tests.android;

import framework.platform.AndroidBaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import framework.core.BaseTest;
import pages.PrivacyPage;
import org.testng.Assert;
import pages.HomePage;
import utils.testrail.TestRailID;

import java.io.ByteArrayInputStream;

@Epic("Home")
@Feature("Layout")
public class HomeTests extends AndroidBaseTest {

    @Test
    @TestRailID("C3")
    @Description("Verify the layout of the Home page")
    public void verifyHomePage() {
        WebDriver driver = BaseTest.getDriver();

        PrivacyPage privacyPage = new PrivacyPage(driver);
        HomePage homePage = new HomePage(driver);

        Allure.addAttachment("test", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        privacyPage.acceptAllCookies();

        Assert.assertTrue(homePage.isMenuButtonDisplayed(), "Menu button was not visible");
        Assert.assertTrue(homePage.isSearchButtonDisplayed(), "Search button was not visible");
        Assert.assertTrue(homePage.isNotificationsButtonDisplayed(), "Notifications button was not visible");
        Assert.assertTrue(homePage.isProfileButtonDisplayed(), "Profile button was not visible");
        Assert.assertTrue(homePage.is9GagLabelDisplayed(), "9Gag label was not visible");
    }
}
