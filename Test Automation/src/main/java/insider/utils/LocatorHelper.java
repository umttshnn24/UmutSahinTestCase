package insider.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class LocatorHelper {
    ConfigReader configReader;

    public LocatorHelper() {
        configReader = new ConfigReader();
    }

    public WebElement findElement(String locatorKey, WebDriver driver) {
        WebElement element = null;
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            File folder = new File(configReader.getProperty("locatorPath"));
            File[] listOfFiles = folder.listFiles();

            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".json")) {
                        String jsonContent = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardCharsets.UTF_8);
                        JSONArray jsonArray = new JSONArray(jsonContent);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject locatorInfo = jsonArray.getJSONObject(i);

                            String key = locatorInfo.getString("key");
                            String type = locatorInfo.getString("type");
                            String value = locatorInfo.getString("value");

                            if (key.equals(locatorKey)) {
                                By by = getBy(type, value);

                                element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));

                                ((JavascriptExecutor) driver).executeScript(
                                        "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                                        element);

                                break;
                            }
                        }
                    }
                    if (element != null) {
                        break;
                    }
                }
            }
        } catch (JSONException | NoSuchElementException | IOException e) {
            throw new RuntimeException("Hata oluştu: " + e.getMessage(), e);
        }

        if (element == null) {
            throw new RuntimeException(locatorKey + " elementi bulunamadı!");
        }

        return element;
    }

    public By findElementInfoBy(String locatorKey) {
        By by = null;

        try {
            File folder = new File(configReader.getProperty("locatorPath"));
            File[] listOfFiles = folder.listFiles();

            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".json")) {
                        String jsonContent = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardCharsets.UTF_8);
                        JSONArray jsonArray = new JSONArray(jsonContent);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject locatorInfo = jsonArray.getJSONObject(i);

                            String key = locatorInfo.getString("key");
                            String type = locatorInfo.getString("type");
                            String value = locatorInfo.getString("value");

                            if (key.equals(locatorKey)) {
                                by = getBy(type, value);
                                return by;
                            }
                        }
                    }
                }
            }
            throw new RuntimeException("Element bulunamadı: " + locatorKey);
        } catch (JSONException | NoSuchElementException | IOException e) {
            throw new RuntimeException("Hata oluştu: " + e.getMessage(), e);
        }
    }

    private By getBy(String type, String value) {
        switch (type) {
            case "id":
                return By.id(value);
            case "css":
                return By.cssSelector(value);
            case "xpath":
                return By.xpath(value);
            case "class":
                return By.className(value);
            default:
                throw new RuntimeException("Desteklenmeyen locator tipi: " + type);
        }
    }
}
