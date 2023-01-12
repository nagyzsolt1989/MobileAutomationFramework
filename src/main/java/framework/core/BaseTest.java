package framework.core;

import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.openqa.selenium.WebDriver;
import java.io.FileNotFoundException;

import utils.PropertyReader;
import utils.listeners.TestListener;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.FileReader;
import org.slf4j.Logger;
import utils.testrail.TestRailUtil;

@Listeners(TestListener.class)
public class BaseTest{
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    public static String mobilePlatform;
    public static String automationName;

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public JSONObject getConfigJSON(String platform) {
        JSONObject jsonObject = null;

        try (FileReader reader = new FileReader("src/device-config.json")) {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            switch (platform){
                case "android":
                    jsonObject = (JSONObject) jsonArray.get(0);
                    mobilePlatform = "Android";
                    automationName = "UIAutomator2";
                    break;
                case "ios":
                    jsonObject = (JSONObject) jsonArray.get(1);
                    break;
                default:
                    LOGGER.info("Please select android or ios for the platform");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return jsonObject;
        }
    }

    @BeforeSuite
    public void createTestRailRun() {
        PropertyReader testrail = new PropertyReader("testrail.properties");
        if (Boolean.valueOf(testrail.getProperty("testrail.enabled"))){
            TestRailUtil.addTestRun();
        }
    }
}
