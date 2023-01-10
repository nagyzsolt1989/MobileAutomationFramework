package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import framework.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrivacyPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Accept All']")
    WebElement buttonAcceptAll;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='More Options']")
    WebElement buttonMoreOptions;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Confirm My Choices']")
    WebElement buttonConfirmMyChoices;

    public PrivacyPage(WebDriver driver) {
        super(driver);
    }

    public void acceptAllCookies() {
        //Possible bug in the app, Privacy Page is displayed twice after a fresh install
        clickOnElement(buttonAcceptAll);
        clickOnElement(buttonAcceptAll);
        LOGGER.info("Privacy Page - Accept All button clicked");
    }

    public void acceptCustomCookies() {
        clickOnElement(buttonMoreOptions);
        clickOnElement(buttonConfirmMyChoices);
        LOGGER.info("Privacy Page - Confirm My Choices button clicked");
    }
}
