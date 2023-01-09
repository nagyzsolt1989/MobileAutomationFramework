package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import framework.core.BasePage;

public class ProfilePage extends BasePage {

    @AndroidFindBy(id = "tvUsername")
    private WebElement buttonSignUpOrLogIn;
    @AndroidFindBy(xpath = "xpath = //android.widget.TextView[@text='Saved']")
    private WebElement buttonSaved;
    @AndroidFindBy(xpath = "xpath = //android.widget.TextView[@text='Settings']")
    private WebElement buttonSettings;
    @AndroidFindBy(xpath = "xpath = //android.widget.TextView[@text='Send feedback']")
    private WebElement buttonSendFeedback;
    @AndroidFindBy(xpath = "xpath = //android.widget.TextView[@text='Dark mode']")
    private WebElement buttonDarkMode;

    public ProfilePage(AppiumDriver driver) {
        super(driver);
    }

    public void clickOnSignUpOrLogInButton() {
        clickOnElement(buttonSignUpOrLogIn);
        LOGGER.info("Profile Page - Sign Up or Log In button clicked");
    }
}
