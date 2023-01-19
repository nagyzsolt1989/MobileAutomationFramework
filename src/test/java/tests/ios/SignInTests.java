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
import pages.SignInPage;
import utils.testrail.TestRailID;

@Epic("Sign In")
@Feature("Layout")
public class SignInTests extends IOSBaseTest {

    @Test
    @TestRailID("C2")
    @Description("Verify the layout of the Sign In page")
    public void verifySignInPage() {
        WebDriver driver = BaseTest.getDriver();

        SignInPage signInPage = new SignInPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage.clickOnSignInWithHttpButton();

        Assert.assertTrue(signInPage.isUsernameInputFieldDisplayed(), "Username input field was not visible");
        Assert.assertTrue(signInPage.isPasswordInputFieldDisplayed(), "Password input field was not visible");
        Assert.assertTrue(signInPage.isSignInButtonDisplayed(), "Sign in button was not visible");
    }
}
