package pages;

import framework.core.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    @AndroidBy(accessibility="Communities")
    WebElement buttonCommunities;
    @AndroidBy(accessibility="Search")
    WebElement buttonSearch;
    @AndroidBy(accessibility="Reddit Recap")
    WebElement buttonRecap;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isCommunitiesButtonDisplayed(){
        return isDisplayed(buttonCommunities);
    }

    public boolean isSearchButtonDisplayed(){
        return isDisplayed(buttonSearch);
    }

    public boolean isRecapButtonDisplayed(){
        return isDisplayed(buttonRecap);
    }
}
