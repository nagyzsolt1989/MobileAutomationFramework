package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import framework.core.BasePage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ForgotPasswordPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Forgot password']")
    private WebElement labelForgotPassword;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Tell us your email address and we will " +
            "send you a link with instruction to reset your password.']")
    private WebElement textForgotPassword;
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Email address']")
    private WebElement inputEmail;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Send instruction']")
    private WebElement buttonSendInstructions;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public boolean isForgotPasswordLabelDisplayed() {
        return isDisplayed(labelForgotPassword);
    }

    public boolean isForgotPasswordTextDisplayed() {
        return isDisplayed(textForgotPassword);
    }

    public boolean isEmailInputFieldDisplayed() {
        return isDisplayed(inputEmail);
    }

    public boolean isSendInstructionsButtonDisplayed() {
        return isDisplayed(buttonSendInstructions);
    }
}
