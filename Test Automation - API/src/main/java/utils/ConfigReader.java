package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (IOException e) {
            System.out.println("Config file not found!");
            e.printStackTrace();
        }
    }

}
