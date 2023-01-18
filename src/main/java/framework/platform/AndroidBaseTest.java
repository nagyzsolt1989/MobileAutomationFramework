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
import utils.PropertyReader;
import utils.listeners.EventListener;
import framework.core.BaseTest;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import org.slf4j.Logger;

import java.net.URL;

public class AndroidBaseTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AndroidBaseTest.class);
    private static PropertyReader config = new PropertyReader("android.properties");

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        BaseTest.setPlatformVersion(config.getProperty("platformVersion"));
        BaseTest.setAutomationName(config.getProperty("automationName"));
        BaseTest.setPlatform(config.getProperty("platformName"));
        BaseTest.setDeviceName(config.getProperty("deviceName"));

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, config.getProperty("platformName"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, config.getProperty("platformVersion"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, config.getProperty("automationName"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, config.getProperty("deviceName"));
        capabilities.setCapability(MobileCapabilityType.APP, config.getProperty("app"));
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, config.getProperty("appWaitActivity"));

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
