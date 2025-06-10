package insider.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties dosyası bulunamadı!");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("config.properties dosyası okunamadı!");
        }
    }

    // Genel property okuyucu metod
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
