package tests.android;

import framework.platform.AndroidBaseTest;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;
import framework.core.BaseTest;
import pages.PrivacyPage;
import pages.ProfilePage;
import org.testng.Assert;
import pages.LoginPage;
import pages.HomePage;

public class LoginTests extends AndroidBaseTest {

    @Test
    public void verifyLogInPage() {
        AppiumDriver driver = BaseTest.getDriver();

        ProfilePage profilePage = new ProfilePage(driver);
        PrivacyPage privacyPage = new PrivacyPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        privacyPage.acceptAllCookies();
        homePage.clickOnProfileButton();
        profilePage.clickOnSignUpOrLogInButton();
        loginPage.clickOnNavigateToLoginButton();

        Assert.assertTrue(loginPage.isUsernameInputFieldDisplayed(), "Username input field was not visible");
        Assert.assertTrue(loginPage.isPasswordInputFieldDisplayed(), "Password input field was not visible");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Log in button was not visible");
    }

    @Test
    public void verifyForgotPasswordPage() {
        AppiumDriver driver = BaseTest.getDriver();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        PrivacyPage privacyPage = new PrivacyPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        privacyPage.acceptAllCookies();
        homePage.clickOnProfileButton();
        profilePage.clickOnSignUpOrLogInButton();
        loginPage.clickOnNavigateToLoginButton();
        loginPage.clickOnForgotPasswordButton();

        Assert.assertTrue(forgotPasswordPage.isForgotPasswordLabelDisplayed(),
                "Forgot Password label was not visible");
        Assert.assertTrue(forgotPasswordPage.isForgotPasswordTextDisplayed(),
                "Forgot Password text was not visible");
        Assert.assertTrue(forgotPasswordPage.isEmailInputFieldDisplayed(),
                "Email input field was not visible");
        Assert.assertTrue(forgotPasswordPage.isSendInstructionsButtonDisplayed(),
                "Send instructions button was not visible");
    }
}
