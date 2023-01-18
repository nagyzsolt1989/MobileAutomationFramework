package framework.core;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.openqa.selenium.WebDriver;

import utils.PropertyReader;
import utils.listeners.TestListener;
import utils.testrail.TestRailUtil;

@Listeners(TestListener.class)
public class BaseTest{
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static String mobilePlatform;
    public static String automationName;
    public static String deviceName;
    public static String platformVersion;

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public static void setPlatform(String platform) {
        mobilePlatform = platform;
    }

    public static void setAutomationName(String automation) {
        automationName = automation;
    }

    public static void setDeviceName(String device) {
        deviceName = device;
    }

    public static void setPlatformVersion(String version) {
        platformVersion = version;
    }


    @BeforeSuite
    public void createTestRailRun() {
        PropertyReader testrail = new PropertyReader("testrail.properties");
        if (Boolean.valueOf(testrail.getProperty("testrail.enabled"))){
            TestRailUtil.addTestRun();
        }
    }
}
