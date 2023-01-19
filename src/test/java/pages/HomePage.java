package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import framework.core.BasePage;

public class HomePage extends BasePage {

    @AndroidFindBy(accessibility = "Form Samples")
    @iOSXCUITFindBy(accessibility = "Form Samples")
    private WebElement labelFormSamples;
    @AndroidFindBy(accessibility = "Sign in with HTTP")
    @iOSXCUITFindBy(accessibility = "Sign in with HTTP")
    private WebElement buttonSignInWithHTTP;
    @AndroidFindBy(accessibility = "Autofill")
    @iOSXCUITFindBy(accessibility = "Autofill")
    private WebElement buttonAutoFill;
    @AndroidFindBy(accessibility = "Form widgets")
    @iOSXCUITFindBy(accessibility = "Form widgets")
    private WebElement buttonFormWidgets;
    @AndroidFindBy(accessibility = "Validation")
    @iOSXCUITFindBy(accessibility = "Form widgets")
    private WebElement buttonValidation;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isFormSamplesLabelDisplayed() {
        return isDisplayed(labelFormSamples);
    }

    public boolean isSignInWithHTTPButtonDisplayed() {
        return isDisplayed(buttonSignInWithHTTP);
    }

    public boolean isAutofillButtonDisplayed() {
        return isDisplayed(buttonAutoFill);
    }

    public boolean isFormWidgetsButtonDisplayed() {
        return isDisplayed(buttonFormWidgets);
    }

    public boolean isValidationButtonDisplayed() {
        return isDisplayed(buttonValidation);
    }

    public void clickOnSignInWithHttpButton() {
        clickOnElement(buttonSignInWithHTTP);
        LOGGER.info("Home Page - Sign In With HTTP button clicked");
    }

    public void clickOnFormWidgetsButton() {
        clickOnElement(buttonFormWidgets);
        LOGGER.info("Home Page - Form widgets button clicked");
    }

    public void clickOnValidationButton() {
        clickOnElement(buttonValidation);
        LOGGER.info("Home Page - Validation button clicked");
    }
}
