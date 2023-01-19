package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import framework.core.BasePage;

public class SignInPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Email\nYour email address']")
    @iOSXCUITFindBy(accessibility = "Email Your email address")
    private WebElement inputUsername;
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Password']")
    @iOSXCUITFindBy(accessibility = "Password")
    private WebElement inputPassword;
    @AndroidFindBy(accessibility = "Sign in")
    @iOSXCUITFindBy(accessibility = "Sign in")
    private WebElement buttonSignIn;
    @AndroidFindBy(accessibility = "Unable to sign in.")
    @iOSXCUITFindBy(accessibility = "Unable to sign in.")
    private WebElement labelUnableToSignIn;
    @AndroidFindBy(accessibility = "OK")
    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement buttonOK;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUsernameInputFieldDisplayed() {
        return isDisplayed(inputUsername);
    }

    public boolean isPasswordInputFieldDisplayed() {
        return isDisplayed(inputPassword);
    }

    public boolean isSignInButtonDisplayed() {
        return isDisplayed(buttonSignIn);
    }
}
