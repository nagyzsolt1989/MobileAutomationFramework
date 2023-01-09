package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import framework.core.BasePage;

public class HomePage extends BasePage {

    @AndroidFindBy(id = "drawer_handle")
    private WebElement buttonMenu;
    @AndroidFindBy(id = "actionSearch")
    private WebElement buttonSearch;
    @AndroidFindBy(id = "actionNotifications")
    private WebElement buttonNotifications;
    @AndroidFindBy(id = "avatarImage")
    private WebElement buttonProfile;
    @AndroidFindBy(id = "watermark")
    private WebElement label9gag;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isMenuButtonDisplayed() {
        return isDisplayed(buttonMenu);
    }

    public boolean isSearchButtonDisplayed() {
        return isDisplayed(buttonSearch);
    }

    public boolean isNotificationsButtonDisplayed() {
        return isDisplayed(buttonNotifications);
    }

    public boolean isProfileButtonDisplayed() {
        return isDisplayed(buttonProfile);
    }

    public boolean is9GagLabelDisplayed() {
        return isDisplayed(label9gag);
    }

    public void clickOnProfileButton() {
        clickOnElement(buttonProfile);
        LOGGER.info("Home Page - Profile button clicked");
    }
}
