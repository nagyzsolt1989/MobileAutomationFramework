package framework.core;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;

public class BasePage {

    private static final Logger LOGGER = LogManager.getLogger(BasePage.class);
    private static final int TIMEOUT = 3;

    protected AppiumDriver driver;
    private WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    protected void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void clickOnElement(WebElement element) {
        try {
            element.click();
            LOGGER.info("{} clicked", element.getText());
        } catch (NoSuchElementException e) {
            LOGGER.error("Element was not visible in {} seconds", TIMEOUT);
        }
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
}
