package tests;

import io.appium.java_client.AppiumDriver;
import org.json.simple.JSONArray;


public class BaseTest{
    private static ThreadLocal<AppiumDriver> threadLocalDriver = new ThreadLocal<>();

    public static ThreadLocal<AppiumDriver> getDriver() {
        return threadLocalDriver;
    }

    public static void setDriver(AppiumDriver driver) {
        threadLocalDriver.set(driver);
    }
}
