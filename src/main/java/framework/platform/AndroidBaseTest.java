package framework.platform;

import framework.core.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidBaseTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(AndroidBaseTest.class);

    @BeforeTest
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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        BaseTest.setDriver(driver);

        LOGGER.info("Appium driver initialised");
    }

    @AfterTest
    public void tearDown() {
        BaseTest.getDriver().quit();
        LOGGER.info("Appium session ended");
    }
}
