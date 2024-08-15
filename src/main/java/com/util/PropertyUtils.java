package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public PropertyUtils(){
        try {
            properties = new Properties();
            String filePath = System.getProperty("user.dir") + "/src/main/java/com/config/config.properties";
            FileInputStream inputStream = null;

            inputStream = new FileInputStream(filePath);
            properties.load(inputStream);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
