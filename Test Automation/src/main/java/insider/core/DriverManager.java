package insider.core;

import insider.utils.Log4jConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class DriverManager {
    private static WebDriver driver;

    public static void beforeClass() {
        Log4jConfig.configureLogger();
    }

    // Parametreli setUp metodu
    public static void setUp(String browser) {
        if (driver == null) {
            if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else { // default chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = getChromeOptions();
                driver = new ChromeDriver(options);
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get("https://www.useinsider.com");
        }
    }


    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--ignore-certificate-errors-spki-list");
        chromeOptions.addArguments("--suppress-message-center-popups");
        chromeOptions.setAcceptInsecureCerts(true);
        return chromeOptions;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--disable-popup-blocking");
        firefoxOptions.setAcceptInsecureCerts(true);
        return firefoxOptions;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
