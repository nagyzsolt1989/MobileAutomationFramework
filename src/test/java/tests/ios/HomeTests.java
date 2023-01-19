package tests.ios;

import framework.core.BaseTest;
import framework.platform.IOSBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.testrail.TestRailID;

@Epic("Home")
@Feature("Layout")
public class HomeTests extends IOSBaseTest {

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
