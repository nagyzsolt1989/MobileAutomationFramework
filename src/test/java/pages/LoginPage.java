package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import framework.core.BasePage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LoginPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Continue with Facebook']")
    private WebElement buttonFacebookSSOLogin;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Continue with Google']")
    private WebElement buttonGoogleSSOLogin;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Continue with Apple']")
    private WebElement buttonAppleSSOLogin;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Use email']")
    private WebElement buttonUseEmail;
    @AndroidFindBy(id = "btnSignInUp")
    private WebElement buttonNavigateToLogIn;
    @AndroidFindBy(xpath = "//android.widget.EditText[1]")
    private WebElement inputUsername;
    @AndroidFindBy(xpath = "//android.widget.EditText[2]")
    private WebElement inputPassword;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log in']")
    private WebElement buttonLogin;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Forgot password?']")
    private WebElement buttonForgotPassword;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUsernameInputFieldDisplayed() {
        return isDisplayed(inputUsername);
    }

    public boolean isPasswordInputFieldDisplayed() {
        return isDisplayed(inputPassword);
    }

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(buttonLogin);
    }

    public void clickOnNavigateToLoginButton () {
        clickOnElement(buttonNavigateToLogIn);
        LOGGER.info("Login Page - Navigate to Login button clicked");
    }

    public void clickOnForgotPasswordButton () {
        clickOnElement(buttonForgotPassword);
        LOGGER.info("Login Page - Forgot Password button clicked");
    }
}
