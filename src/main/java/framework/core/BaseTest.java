package framework.core;

import org.json.simple.parser.ParseException;
import io.appium.java_client.AppiumDriver;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.FileReader;
import org.slf4j.Logger;

public class BaseTest{
    private static ThreadLocal<AppiumDriver> threadLocalDriver = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    public static AppiumDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static void setDriver(AppiumDriver driver) {
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
