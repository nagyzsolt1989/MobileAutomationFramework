package framework.platform;

import framework.core.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.json.simple.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidBaseTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AndroidBaseTest.class);

    @BeforeTest
    public void setUp() throws MalformedURLException {
        JSONObject androidConfig = getConfigJSON("android");
        System.out.println(androidConfig.get("platformName"));
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, androidConfig.get("platformName"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidConfig.get("platformVersion"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, androidConfig.get("automationName"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, androidConfig.get("deviceName"));
        capabilities.setCapability(MobileCapabilityType.APP, androidConfig.get("app"));
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, androidConfig.get("appWaitActivity"));

        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        BaseTest.setDriver(driver);
        LOGGER.info("Appium Driver initialised");
    }
}
