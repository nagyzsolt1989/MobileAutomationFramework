package framework.core;

import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import io.appium.java_client.pagefactory.DefaultElementByBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
import java.time.Duration;
import org.slf4j.Logger;

public class BasePage {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    private static final int TIMEOUT = 20;

    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        PageFactory.initElements(new AppiumElementLocatorFactory(driver, Duration.ofSeconds(1),
                new DefaultElementByBuilder(BaseTest.mobilePlatform, BaseTest.automationName)), this);
    }

    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void clickOnElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    protected boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    protected boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    protected boolean isSelected(WebElement element) {
        return element.isSelected();
    }

    protected void sendKeys(WebElement element, String keys) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(keys);
    }

    public String getLocatorFromWebElement(WebElement element) {
        return element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
    }
}
