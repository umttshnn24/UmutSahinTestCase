package insider.base;

import insider.core.DriverManager;
import insider.utils.LocatorHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseMethods {
    protected static final Logger logger = Logger.getLogger(BaseMethods.class);
    protected final WebDriver driver = DriverManager.getDriver();
    protected final LocatorHelper locatorHelper = new LocatorHelper();

    private int sizeJobList = 0; // artık static değil, dinamik olacak

    // Harici erişim için sizeJobList güncelleyici
    public void updateSizeJobList(String locatorKey) {
        List<WebElement> elements = driver.findElements(locatorHelper.findElementInfoBy(locatorKey));
        sizeJobList = elements.size();
        logger.info("Size of job list updated to: " + sizeJobList);
    }

    public void clickElement(String locatorKey) {
        WebElement element = locatorHelper.findElement(locatorKey, driver);
        element.click();
        logger.info(locatorKey + " element clicked.");
    }

    public void sendKeys(String locatorKey, String text) {
        WebElement element = locatorHelper.findElement(locatorKey, driver);
        element.clear();
        element.sendKeys(text);
        logger.info(locatorKey + " sent keys: " + text);
    }

    public void waitForElementToBeVisible(String locatorKey) {
        By by = locatorHelper.findElementInfoBy(locatorKey);
        getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
        logger.info(locatorKey + " waited until visible.");
    }

    public void scrollToElementToBeVisible(String locatorKey) {
        By by = locatorHelper.findElementInfoBy(locatorKey);
        WebElement element = getWait().until(ExpectedConditions.presenceOfElementLocated(by));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        getWait().until(ExpectedConditions.visibilityOf(element));
        logger.info(locatorKey + " scrolled into view.");
    }

    public void waitBySecond(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    public void hoverOverLastElement(String locatorKey) {
        List<WebElement> elements = driver.findElements(locatorHelper.findElementInfoBy(locatorKey));
        if (!elements.isEmpty()) {
            new Actions(driver).moveToElement(elements.get(elements.size() - 1)).perform();
            logger.info("Hovered over last element of " + locatorKey);
        } else {
            logger.warn(locatorKey + " list is empty.");
        }
    }

    public void switchToSecondTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
            logger.info("Switched to second tab.");
        } else {
            logger.warn("Second tab not found.");
        }
    }

    public void checkForElement(String locatorKey, String errorMessage) {
        try {
            waitForElementToBeVisible(locatorKey);
        } catch (Exception e) {
            logger.error(errorMessage, e);
            throw new AssertionError(errorMessage);
        }
    }

    public void javascriptClick(String locatorKey) {
        WebElement element = driver.findElement(locatorHelper.findElementInfoBy(locatorKey));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        logger.info(locatorKey + " clicked with JavaScript.");
    }

    public void gotoURL(String url) {
        try {
            driver.get(url);
            logger.info("Navigated to: " + url);
        } catch (Exception e) {
            logger.error("Navigation failed to: " + url, e);
            throw e;
        }
    }

    public void waitForMinimumNumberOfElements(String locatorKey, int minCount, int timeoutSeconds) {
        By by = locatorHelper.findElementInfoBy(locatorKey);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(driver -> driver.findElements(by).size() >= minCount);
    }
    public void checkIsTextContainsList(String key, String expectedValue) {
        waitForMinimumNumberOfElements(key, 1, 10);

        List<WebElement> elements = driver.findElements(locatorHelper.findElementInfoBy(key));
        int index = 1;
        for (WebElement el : elements) {
            String text = el.getText();
            if (!text.contains(expectedValue)) {
                String msg = "Item " + index + " text '" + text + "' does not contain expected value: " + expectedValue;
                logger.error(msg);
                throw new AssertionError(msg);
            }
            logger.info("Item " + index + ": '" + text + "' contains expected value: " + expectedValue);
            index++;
        }
    }

    protected List<WebElement> findsElements(String locatorKey) {
        return driver.findElements(locatorHelper.findElementInfoBy(locatorKey));
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private Map<String, String> savedValues = new HashMap<>();

    // LocatorKey bazlı son elemanı kaydet
    public void saveValueLastJob(String locatorKey) {
        updateSizeJobList(locatorKey);
        if (sizeJobList == 0) {
            throw new AssertionError("No elements found for locator: " + locatorKey);
        }
        String text = findsElements(locatorKey).get(sizeJobList - 1).getText().trim();
        savedValues.put(locatorKey, text);
        logger.info("Saved last job value for " + locatorKey + ": " + text);
    }

    protected void hoverOverElementWithSelectedSize(String key) {
        updateSizeJobList(key);
        if (sizeJobList == 0) {
            throw new AssertionError("No elements found to hover for locator: " + key);
        }
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(findsElements(key).get(sizeJobList - 1)).perform();
            Thread.sleep(900);
            logger.info(key + " element was hovered");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void clickElementWithSelectedSize(String key) {
        updateSizeJobList(key);
        if (sizeJobList == 0) {
            throw new AssertionError("No elements found to click for locator: " + key);
        }
        findsElements(key).get(sizeJobList - 1).click();
        logger.info(key + " element clicked");
    }

}
