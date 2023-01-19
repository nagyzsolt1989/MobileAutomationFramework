package pages;

import framework.core.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ValidationPage extends BasePage {

    @AndroidFindBy(accessibility = "\uD83D\uDCD6 Story Generator")
    @iOSXCUITFindBy(accessibility = "\uD83D\uDCD6 Story Generator")
    private WebElement labelStoryGenerator;
    @AndroidFindBy(xpath = "//android.widget.EditText[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[1]")
    private WebElement inputAdjective;
    @AndroidFindBy(xpath = "//android.widget.EditText[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[2]")
    private WebElement inputNoun;
    @AndroidFindBy(xpath = "//android.widget.CheckBox")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch")
    private WebElement checkboxTermsOfService;
    @AndroidFindBy(accessibility = "Submit")
    @iOSXCUITFindBy(accessibility = "Submit")
    private WebElement buttonSubmit;
    @AndroidFindBy(accessibility = "Please enter an adjective.")
    @iOSXCUITFindBy(accessibility = "Please enter an adjective.")
    private WebElement warningAdjective;
    @AndroidFindBy(accessibility = "Please enter a noun.")
    @iOSXCUITFindBy(accessibility = "Please enter a noun.")
    private WebElement warningNoun;
    @AndroidFindBy(accessibility = "You must agree to the terms of service.")
    @iOSXCUITFindBy(accessibility = "You must agree to the terms of service.")
    private WebElement warningTermsOfService;
    @AndroidFindBy(accessibility = "Your story")
    @iOSXCUITFindBy(accessibility = "Your story")
    private WebElement labelYourStory;
    @AndroidFindBy(accessibility = "The black developer saw a cat")
    @iOSXCUITFindBy(accessibility = "The black developer saw a cat")
    private WebElement textStory;
    @AndroidFindBy(accessibility = "Done")
    @iOSXCUITFindBy(accessibility = "Done")
    private WebElement buttonDone;

    public ValidationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isStoryGeneratorLabelDisplayed() {
        return isDisplayed(labelStoryGenerator);
    }

    public boolean isAdjectiveInputFieldDisplayed() {
        return isDisplayed(inputAdjective);
    }

    public boolean isNounInputFieldDisplayed() {
        return isDisplayed(inputNoun);
    }

    public boolean isTermsOfServiceCheckboxDisplayed() {
        return isDisplayed(checkboxTermsOfService);
    }

    public boolean isSubmitButtonDisplayed()  {return isDisplayed(buttonSubmit);}

    public boolean isAdjectiveWarningDisplayed()  {return isDisplayed(warningAdjective);}

    public boolean isNounWarningDisplayed()  {return isDisplayed(warningNoun);}

    public boolean isTermsOfServiceWarningDisplayed()  {return isDisplayed(warningTermsOfService);}

    public boolean isYourStoryLabelDisplayed()  {return isDisplayed(labelYourStory);}

    public boolean isYourStoryTextDisplayed()  {return isDisplayed(textStory);}

    public boolean isDoneButtonDisplayed()  {return isDisplayed(buttonDone);}

    public void clickOnSubmitButton() {
        clickOnElement(buttonSubmit);
        LOGGER.info("Validation Page - Submit button clicked");
    }

    public void fillAdjectiveField(String value) {
        clickOnElement(inputAdjective);
        sendKeys(inputAdjective, value);
        LOGGER.info("{} value is changed to: {}", getLocatorFromWebElement(inputAdjective), value);
    }

    public void fillNounField(String value) {
        clickOnElement(inputNoun);
        sendKeys(inputNoun, value);
        LOGGER.info("{} value is changed to: {}", getLocatorFromWebElement(inputNoun), value);
    }

    public void acceptTermsOfService() {
        clickOnElement(checkboxTermsOfService);
        LOGGER.info("Terms Of Service accepted");
    }
}
