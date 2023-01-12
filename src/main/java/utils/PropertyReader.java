package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReader.class);

    private Properties properties;

    public PropertyReader(String propertyFile) {
        this.properties = new Properties();
        this.properties = this.appendFromResource(this.properties, propertyFile);
    }

    private Properties appendFromResource(Properties properties, String propertyFile) {
        try (InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(propertyFile)) {
            properties.load(inStream);
        } catch (FileNotFoundException e) {
            LOGGER.error("Couldn't find property file: {}", propertyFile);
        } catch (IOException e) {
            LOGGER.error("Property Reader: problem initializing");
        }
        return properties;
    }

    public String getProperty(String keyName) {
        return properties.getProperty(keyName, "There is no key in the properties file");
    }
}