package com.automation.selenium.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties = new Properties();

    static {
        try {
            String env = System.getProperty("env", "qa");

            String commonConfig = "config/config.properties";
            String envConfig = "config/config-" + env + ".properties";

            ClassLoader loader = ConfigLoader.class.getClassLoader();

            try (InputStream common = loader.getResourceAsStream(commonConfig);
                 InputStream envSpecific = loader.getResourceAsStream(envConfig)) {

                if (common != null) {
                    properties.load(common);
                }

                if (envSpecific != null) {
                    properties.load(envSpecific);
                } else {
                    throw new RuntimeException("Environment config not found: " + envConfig);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    private ConfigLoader() {}

    public static String get(String key) {
        return properties.getProperty(key);
    }

}
