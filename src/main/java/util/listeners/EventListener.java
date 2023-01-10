package util.listeners;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.ByteArrayInputStream;
import framework.core.BaseTest;
import io.qameta.allure.Allure;

public class EventListener implements WebDriverListener {

    @Override
    public void beforeClick(WebElement element) {
        Allure.attachment("Before clicking on " + getLocatorFromWebElement(element),
                new ByteArrayInputStream(((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    public String getLocatorFromWebElement(WebElement element) {
        return element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
    }
}
