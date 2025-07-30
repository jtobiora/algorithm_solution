package com.swiftfingers.codingchallenge.virtualaccount;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Obiora on 14-Jul-2025 at 09:52
 */
public class PropertyConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertyConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("application.properties file not found in resources!");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}