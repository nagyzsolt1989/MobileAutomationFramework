package framework.platform;

import framework.core.BaseTest;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PropertyReader;
import utils.listeners.EventListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class IOSBaseTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IOSBaseTest.class);
    private static PropertyReader config = new PropertyReader("ios.properties");

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
        capabilities.setCapability(MobileCapabilityType.APP,
                System.getProperty("user.dir") + config.getProperty("app"));

        IOSDriver driver = new IOSDriver(new URL("http://localhost:4723"), capabilities);

        WebDriverListener eventListener = new EventListener();
        WebDriver decoratedAppiumDriver = new EventFiringDecorator(eventListener).decorate(driver);

        decoratedAppiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        BaseTest.setDriver(decoratedAppiumDriver);

        LOGGER.info("iOS driver initialised");
    }

    @AfterMethod
    public void tearDown() {
        BaseTest.getDriver().quit();
        LOGGER.info("Appium session ended");
    }
}
