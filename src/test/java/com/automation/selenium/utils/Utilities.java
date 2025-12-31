package com.automation.selenium.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilities {

//    private static final String CONFIG_DATA_FILE_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";
////    private static final String TEST_DATA_FILE_PATH = System.getProperty("user.dir") + "\\src\\test\\java\\com\\internet\\Resources\\TestData.properties";
//
//    // Method to get property value from GlobalData.properties file
//    public static String getPropertyFromGlobalData(String propertyName) {
//        return getProperty(propertyName, CONFIG_DATA_FILE_PATH);
//    }
//
//    // Method to get property value from TestData.properties file
////    public static String getPropertyFromTestData(String propertyName) {
////        return getProperty(propertyName, TEST_DATA_FILE_PATH);
////    }
//
//    private static String getProperty(String propertyName, String filePath) {
//        Properties prop = new Properties();
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            prop.load(fis);
//            return prop.getProperty(propertyName);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


private static final Properties properties = new Properties();

    static {
        try {
            String configPath = System.getProperty("user.dir")
                    + "/src/test/resources/config/config.properties";

            FileInputStream fis = new FileInputStream(configPath);
            properties.load(fis);
            fis.close();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load config.properties file", e
            );
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException(
                    "Property '" + key + "' not found in config.properties"
            );
        }
        return value.trim();
    }

}
