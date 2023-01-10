package framework.core;

import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.FileReader;
import org.slf4j.Logger;

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
}
