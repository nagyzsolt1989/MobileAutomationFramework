package framework.platform;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import utils.listeners.EventListener;
import org.json.simple.JSONObject;
import framework.core.BaseTest;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import org.slf4j.Logger;
import java.net.URL;

public class AndroidBaseTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AndroidBaseTest.class);

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        JSONObject androidConfig = getConfigJSON("android");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, androidConfig.get("platformName"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidConfig.get("platformVersion"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, androidConfig.get("automationName"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, androidConfig.get("deviceName"));
        capabilities.setCapability(MobileCapabilityType.APP, androidConfig.get("app"));
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, androidConfig.get("appWaitActivity"));

        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        WebDriverListener eventListener = new EventListener();
        WebDriver decoratedAppiumDriver = new EventFiringDecorator(eventListener).decorate(driver);

        decoratedAppiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        BaseTest.setDriver(decoratedAppiumDriver);

        LOGGER.info("Android driver initialised");
    }

    @AfterMethod
    public void tearDown() {
        BaseTest.getDriver().quit();
        LOGGER.info("Appium session ended");
    }
}
