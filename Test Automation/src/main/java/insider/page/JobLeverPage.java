package insider.page;

import insider.base.BaseMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JobLeverPage extends BaseMethods {

    private static final Logger logger = Logger.getLogger(JobLeverPage.class);

    public void checkJobPage() {
        switchToSecondTab();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("jobs.lever.co"));

        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.contains("jobs.lever.co")) {
            throw new AssertionError("Beklenen Lever iş başvuru sayfasına yönlenilmedi! URL: " + currentUrl);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorHelper.findElementInfoBy("jobTitleInJobPage")));

        logger.info("İş başvuru formu sayfasına başarıyla yönlenildi.");
    }


}
