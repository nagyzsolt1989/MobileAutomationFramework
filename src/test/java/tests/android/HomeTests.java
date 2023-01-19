package tests.android;

import framework.platform.AndroidBaseTest;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;
import framework.core.BaseTest;
import org.testng.Assert;
import pages.HomePage;
import utils.testrail.TestRailID;

@Epic("Home")
@Feature("Layout")
public class HomeTests extends AndroidBaseTest {

    @Test
    @TestRailID("C1")
    @Description("Verify the layout of the Home page")
    public void verifyHomePage() {
        WebDriver driver = BaseTest.getDriver();

        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isFormSamplesLabelDisplayed(), "Form Samples label was not visible");
        Assert.assertTrue(homePage.isSignInWithHTTPButtonDisplayed(),
                "Sign in with HTTP button was not visible");
        Assert.assertTrue(homePage.isFormWidgetsButtonDisplayed(), "Form Widgets button was not visible");
        Assert.assertTrue(homePage.isValidationButtonDisplayed(), "Validation button was not visible");
    }
}
