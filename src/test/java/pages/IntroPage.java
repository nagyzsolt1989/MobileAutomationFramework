package pages;

import framework.core.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IntroPage extends BasePage {

    @FindBy(xpath="//*[@text='Skip']")
    WebElement buttonSkip;

    public IntroPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickOnSkipButton() {
        clickOnElement(buttonSkip);
    }
}
