package framework.platform;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import framework.core.BaseTest;
import org.slf4j.LoggerFactory;
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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        BaseTest.setDriver(driver);

        LOGGER.info("Android driver initialised");
    }

    @AfterMethod
    public void tearDown() {
        BaseTest.getDriver().quit();
        LOGGER.info("Appium session ended");
    }
}
